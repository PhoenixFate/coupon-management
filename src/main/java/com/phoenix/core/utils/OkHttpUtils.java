package com.phoenix.core.utils;

import com.phoenix.core.filter.ReqRespLogFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.OkHttpClient.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OkHttpUtils {
    private static int DEFAULT_TIMEOUT = 60000;
    private static Logger logger = LoggerFactory.getLogger(OkHttpUtils.class);

    public OkHttpUtils() {
    }

    public static String sendPost(String url, String param) throws Exception {
        return sendPost(url, param, (String)null);
    }

    public static String sendPost(String url, String param, int timeout) throws Exception {
        return sendPost(url, param, (String)null, timeout);
    }

    public static String sendPost(String url, String param, String encryptKey) throws Exception {
        return sendPost(url, param, encryptKey, DEFAULT_TIMEOUT);
    }

    public static String sendPost(String url, String param, String encryptKey, int timeout) throws Exception {
        OkHttpClient client = (new Builder()).connectTimeout((long)timeout, TimeUnit.MILLISECONDS).build();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain;charset=utf-8");
        if (encryptKey != null && !"".equals(encryptKey)) {
            param = EncryptionUtil.encode(param, encryptKey);
        }

        String traceId = (String)ReqRespLogFilter.TraceId.get();
        Map<String, String> headers = new HashMap();
        if (traceId != null) {
            headers.put("REQUEST-TRACE-ID", traceId);
        }

        Request request = (new okhttp3.Request.Builder()).url(url).post(RequestBody.create(MEDIA_TYPE_TEXT, param)).build();
        String uuid = UUIDUtil.getUUID();
        logger.info(uuid + " 发送请求：" + url);
        logger.info(uuid + " 请求参数：" + param);
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        } else {
            String response_str = response.body().string();
            if (encryptKey != null && !"".equals(encryptKey)) {
                response_str = EncryptionUtil.decode(response_str, encryptKey);
            }

            logger.info(uuid + " 响应数据：" + response_str);
            return response_str;
        }
    }
}
