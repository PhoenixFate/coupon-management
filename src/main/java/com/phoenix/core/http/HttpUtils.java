package com.phoenix.core.http;


import com.phoenix.core.filter.ReqRespLogFilter;
import com.phoenix.core.utils.EncryptionUtil;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;

public class HttpUtils {
    private static final String BASIC_PREFIX = "Basic";

    public HttpUtils() {
    }

    public static String getBasicAuthorization(String username, String password) {
        String encodeKey = username + ":" + password;
        return "Basic " + new String(Base64.encodeBase64(encodeKey.getBytes()));
    }

    public static String sendPost(String url, String param) throws ConnectionException, RequestException {
        return sendPost(url, param, (String)null);
    }

    public static String sendPost(String url, String param, int timeout) throws ConnectionException, RequestException {
        return sendPost(url, param, (String)null, timeout);
    }

    public static String sendPost(String url, String param, String encryptKey) throws ConnectionException, RequestException {
        IHttpClient httpClient = new NativeHttpsClient();
        if (encryptKey != null && !"".equals(encryptKey)) {
            param = EncryptionUtil.encode(param, encryptKey);
        }

        String traceId = (String)ReqRespLogFilter.TraceId.get();
        Map<String, String> headers = new HashMap();
        if (traceId != null) {
            headers.put("REQUEST-TRACE-ID", traceId);
        }

        ResponseWrapper resp = httpClient.sendPost(url, param, headers);
        String response_str = resp.responseContent;
        if (encryptKey != null && !"".equals(encryptKey)) {
            response_str = EncryptionUtil.decode(response_str, encryptKey);
        }

        return response_str;
    }

    public static String sendPost(String url, String param, String encryptKey, int timeout) throws ConnectionException, RequestException {
        IHttpClient httpClient = new NativeHttpsClient();
        if (encryptKey != null && !"".equals(encryptKey)) {
            param = EncryptionUtil.encode(param, encryptKey);
        }

        String traceId = (String)ReqRespLogFilter.TraceId.get();
        Map<String, String> headers = new HashMap();
        if (traceId != null) {
            headers.put("REQUEST-TRACE-ID", traceId);
        }

        ResponseWrapper resp = httpClient.sendPost(url, param, headers, timeout);
        String response_str = resp.responseContent;
        if (encryptKey != null && !"".equals(encryptKey)) {
            response_str = EncryptionUtil.decode(response_str, encryptKey);
        }

        return response_str;
    }

    public static String sendGet(String url, String param) throws ConnectionException, RequestException {
        return sendGet(url, param, (String)null);
    }

    public static String sendGet(String url, String param, String encryptKey) throws ConnectionException, RequestException {
        IHttpClient httpClient = new NativeHttpsClient();
        if (encryptKey != null && !"".equals(encryptKey)) {
            param = EncryptionUtil.encode(param, encryptKey);
        }

        ResponseWrapper resp = httpClient.sendGet(url, param);
        String response_str = resp.responseContent;
        if (encryptKey != null && !"".equals(encryptKey)) {
            response_str = EncryptionUtil.decode(response_str, encryptKey);
        }

        return response_str;
    }
}
