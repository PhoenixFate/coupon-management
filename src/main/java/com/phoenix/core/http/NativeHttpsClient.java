package com.phoenix.core.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NativeHttpsClient implements IHttpClient {
    private static final String KEYWORDS_CONNECT_TIMED_OUT = "连接超时";
    private static final String KEYWORDS_READ_TIMED_OUT = "数据读取超时";
    protected static final Logger logger = LoggerFactory.getLogger(NativeHttpsClient.class);
    private int _maxRetryTimes;
    private String _authCode;
    private HttpProxy _proxy;
    private StringBuffer log;

    public NativeHttpsClient(String authCode) throws ConnectionException {
        this(authCode, 3, (HttpProxy)null);
    }

    public NativeHttpsClient() throws ConnectionException {
        this._maxRetryTimes = 0;
        this.log = new StringBuffer("");
    }

    public NativeHttpsClient(String authCode, int maxRetryTimes, HttpProxy proxy) throws ConnectionException {
        this._maxRetryTimes = 0;
        this.log = new StringBuffer("");
        this._maxRetryTimes = maxRetryTimes;
        this._authCode = authCode;
        this._proxy = proxy;
        this.log.append("初始化http maxRetryTimes = " + this._maxRetryTimes + " authCode = " + this._authCode);
        this.log.append(System.getProperty("line.separator"));
        this.initSSL();
    }

    public ResponseWrapper sendGet(String url, String params) throws ConnectionException, RequestException {
        return this.sendRequest(url, params, RequestMethod.GET);
    }

    public ResponseWrapper sendPost(String url, String content) throws ConnectionException, RequestException {
        return this.sendRequest(url, content, RequestMethod.POST);
    }

    public ResponseWrapper sendPost(String url, String content, Map<String, String> headers) throws ConnectionException, RequestException {
        return this.sendPost(url, content, headers, 500000);
    }

    public ResponseWrapper sendPost(String url, String content, Map<String, String> headers, int timeout) throws ConnectionException, RequestException {
        return this.sendRequest(url, content, RequestMethod.POST, headers, timeout);
    }

    public ResponseWrapper sendRequest(String url, String content, RequestMethod method) throws ConnectionException, RequestException {
        return this.sendRequest(url, content, RequestMethod.POST, (Map)null, 500000);
    }

    public ResponseWrapper sendRequest(String url, String content, RequestMethod method, Map<String, String> headers, int timeout) throws ConnectionException, RequestException {
        ResponseWrapper response = null;
        byte retryTimes = 0;

        try {
            response = this._sendRequest(url, content, method, headers, timeout);
            return response;
        } catch (SocketTimeoutException var9) {
            if ("数据读取超时".equals(var9.getMessage())) {
                throw new ConnectionException("数据读取超时.\n", var9, true);
            } else if (retryTimes >= this._maxRetryTimes) {
                throw new ConnectionException("连接超时.", var9, retryTimes);
            } else {
                this.log.append("连接超时 - 重新连接 - " + (retryTimes + 1));
                throw new ConnectionException("连接超时.", var9, retryTimes);
            }
        }
    }

    private ResponseWrapper _sendRequest(String url, String content, RequestMethod method, Map<String, String> headers, int timeout) throws ConnectionException, RequestException, SocketTimeoutException {
        logger.info("发送请求 - " + url);
        if (content != null) {
            logger.info("请求内容 - " + content);
        }

        HttpURLConnection conn = null;
        OutputStream out = null;
        StringBuffer sb = new StringBuffer();
        ResponseWrapper wrapper = new ResponseWrapper();

        try {
            URL aUrl = new URL(url);
            if (this._proxy != null) {
                conn = (HttpURLConnection)aUrl.openConnection(this._proxy.getNetProxy());
                if (this._proxy.isAuthenticationNeeded()) {
                    conn.setRequestProperty("Proxy-Authorization", this._proxy.getProxyAuthorization());
                    Authenticator.setDefault(new NativeHttpsClient.SimpleProxyAuthenticator(this._proxy.getUsername(), this._proxy.getPassword()));
                }
            } else {
                conn = (HttpURLConnection)aUrl.openConnection();
            }

            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(500000);
            conn.setUseCaches(false);
            conn.setRequestMethod(method.name());
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Charset", "UTF-8");
            if (this._authCode != null) {
                conn.setRequestProperty("Authorization", this._authCode);
            }

            Iterator in;
            if (headers != null) {
                in = headers.keySet().iterator();

                while(in.hasNext()) {
                    String keys = (String)in.next();
                    conn.setRequestProperty(keys, (String)headers.get(keys));
                }
            }

            if (RequestMethod.POST == method) {
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                byte[] data = content.getBytes("UTF-8");
                conn.setRequestProperty("Content-Length", String.valueOf(data.length));
                out = conn.getOutputStream();
                out.write(data);
                out.flush();
            } else {
                conn.setDoOutput(false);
            }

            int status = conn.getResponseCode();
            in = null;
            InputStream inputStream;
            if (status == 200) {
                inputStream = conn.getInputStream();
            } else {
                inputStream = conn.getErrorStream();
            }

            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            char[] buff = new char[1024];

            int len;
            while((len = reader.read(buff)) > 0) {
                sb.append(buff, 0, len);
            }

            String responseContent = sb.toString();
            wrapper.responseCode = status;
            wrapper.responseContent = responseContent;
            String quota = conn.getHeaderField("X-Rate-Limit-Limit");
            String remaining = conn.getHeaderField("X-Rate-Limit-Remaining");
            String reset = conn.getHeaderField("X-Rate-Limit-Reset");
            wrapper.setRateLimit(quota, remaining, reset);
            if (status != 200) {
                if (status > 200 && status < 400) {
                    logger.info("未知响应错误 - responseCode:" + status + ", responseContent:" + responseContent);
                    logger.info(System.getProperty("line.separator"));
                    throw new RequestException(wrapper);
                }

                logger.info("响应错误 - responseCode:" + status + ", responseContent:" + responseContent);
                logger.info(System.getProperty("line.separator"));
                throw new RequestException(wrapper);
            }

            logger.info("response成功 - 200 OK");
            logger.info(System.getProperty("line.separator"));
            logger.info("Response 结果 - " + responseContent);
            logger.info(System.getProperty("line.separator"));
        } catch (SocketTimeoutException var28) {
            if (var28.getMessage().contains("连接超时")) {
                throw var28;
            }

            if (var28.getMessage().contains("数据读取超时")) {
                throw new SocketTimeoutException("数据读取超时");
            }

            throw new ConnectionException("连接超时.", var28);
        } catch (IOException var29) {
            var29.printStackTrace();
            throw new ConnectionException("I/O异常 .", var29);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException var27) {
                }
            }

            if (conn != null) {
                conn.disconnect();
            }

        }

        return wrapper;
    }

    protected void initSSL() throws ConnectionException {
        TrustManager[] tmCerts = new TrustManager[]{new NativeHttpsClient.SimpleTrustManager()};

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init((KeyManager[])null, tmCerts, (SecureRandom)null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HostnameVerifier hostnameVerifier = new NativeHttpsClient.SimpleHostnameVerifier();
            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
        } catch (Exception var4) {
            throw new ConnectionException("SSL 初始化失败.\n", var4);
        }
    }

    public StringBuffer getLog() {
        return this.log;
    }

    private static class SimpleHostnameVerifier implements HostnameVerifier {
        private SimpleHostnameVerifier() {
        }

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static class SimpleProxyAuthenticator extends Authenticator {
        private String username;
        private String password;

        public SimpleProxyAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.username, this.password.toCharArray());
        }
    }

    private static class SimpleTrustManager implements TrustManager, X509TrustManager {
        private SimpleTrustManager() {
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
