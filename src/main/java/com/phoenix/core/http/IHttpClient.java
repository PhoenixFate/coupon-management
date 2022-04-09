package com.phoenix.core.http;
import java.util.Map;

public interface IHttpClient {
    String CHARSET = "UTF-8";
    String CONTENT_TYPE_JSON = "application/json";
    String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    String RATE_LIMIT_QUOTA = "X-Rate-Limit-Limit";
    String RATE_LIMIT_Remaining = "X-Rate-Limit-Remaining";
    String RATE_LIMIT_Reset = "X-Rate-Limit-Reset";
    String CHROME_USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36";
    int RESPONSE_OK = 200;
    String IO_ERROR_MESSAGE = "I/O异常 .";
    String CONNECT_TIMED_OUT_MESSAGE = "连接超时.";
    String READ_TIMED_OUT_MESSAGE = "数据读取超时.\n";
    String INIT_SSL_ERROR = "SSL 初始化失败.\n";
    int DEFAULT_CONNECTION_TIMEOUT = 500000;
    int DEFAULT_READ_TIMEOUT = 500000;
    int DEFAULT_MAX_RETRY_TIMES = 3;

    ResponseWrapper sendGet(String var1, String var2) throws ConnectionException, RequestException;

    ResponseWrapper sendPost(String var1, String var2) throws ConnectionException, RequestException;

    ResponseWrapper sendPost(String var1, String var2, Map<String, String> var3) throws ConnectionException, RequestException;

    ResponseWrapper sendPost(String var1, String var2, Map<String, String> var3, int var4) throws ConnectionException, RequestException;

    StringBuffer getLog();

    public static enum RequestMethod {
        GET,
        POST,
        DELETE;

        private RequestMethod() {
        }
    }
}