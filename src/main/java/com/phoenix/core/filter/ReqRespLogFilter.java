package com.phoenix.core.filter;

import com.phoenix.core.utils.EncryptionUtil;
import com.phoenix.core.utils.UUIDUtil;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class ReqRespLogFilter extends OncePerRequestFilter {
    protected static final Logger logger = LoggerFactory.getLogger(ReqRespLogFilter.class);
    private static String[] path_ignores = new String[]{"/", "/css/**", "/js/**", "/fonts/**", "/img/**", "/docs/**", "/upload/**", "/files/**"};
    private static String[] resp_ignores = new String[]{"image/png", "image/jpg", "image/jpeg", "javascript", "x-javascript", "text/css", "text/html", "ms-excel", "application/font-woff", "application/font-woff2", "application/octet-stream", "application/oct-stream", "audio/wav"};
    private static final String RESPONSE_FORMAT = "Response: REQUEST-TRACE-IDS=%s; current request id=%s;响应参数=%s";
    private static final String REQUEST_FORMAT = "Request: clientIp:%s; REQUEST-TRACE-IDS=%s; current request id=%s; 请求时间：%s;请求耗时: %s;uri=%s;请求参数=%s";
    public static ThreadLocal<String> TraceId = new ThreadLocal();

    public ReqRespLogFilter() {
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTraceId = request.getHeader("REQUEST-TRACE-ID") == null ? "" : request.getHeader("REQUEST-TRACE-ID");
        String requestId = UUIDUtil.getUUID();
        if (requestTraceId != null && !"".equals(requestTraceId)) {
            requestTraceId = requestTraceId + "|" + requestId;
        } else {
            requestTraceId = requestId;
        }

        TraceId.set(requestTraceId);
        request = new RequestWrapper(requestId, request);
        response = new ResponseWrapper(requestId, response);

        try {
            filterChain.doFilter(request, response);
        } catch (Exception var12) {
            logger.error(var12.getMessage(), var12);
            response.setContentType("text/plain");
            response.getWriter().write("{\"rspCode\":0,\"rspMsg\":\"服务异常\"}");
            response.getWriter().flush();
        } finally {
            String contextPath = request.getServletContext().getContextPath();
            String requestUri = request.getRequestURI().replaceFirst(contextPath, "");
            if (!this.pathEgnore(requestUri)) {
                this.logRequest((RequestWrapper)request);
                if (!this.respEgnore(response.getContentType())) {
                    this.logResponse((ResponseWrapper)response);
                } else {
                    logger.info("Response: " + requestId + " contentType:" + response.getContentType() + " no need to log");
                }
            }

        }

    }

    private void logRequest(RequestWrapper request) {
        long start = request.getTime().getTime();
        long end = (new Date()).getTime();
        String requestTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(request.getTime());
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String payload = "";
        if (!isMultipart(request)) {
            try {
                String charEncoding = request.getCharacterEncoding() != null ? request.getCharacterEncoding() : "UTF-8";
                String param = new String(request.toByteArray(), charEncoding);
                payload = EncryptionUtil.decode(param);
                if (payload == null) {
                    payload = param;
                }
            } catch (Exception var12) {
                logger.warn("Failed to parse request payload", var12);
            }
        }

        logger.info(String.format("Request: clientIp:%s; REQUEST-TRACE-IDS=%s; current request id=%s; 请求时间：%s;请求耗时: %s;uri=%s;请求参数=%s", getIpAddr(request), TraceId.get(), request.getId(), requestTime, end - start, uri + "?" + queryString, payload));
    }

    private void logResponse(ResponseWrapper response) {
        try {
            String resp = new String(response.toByteArray(), response.getCharacterEncoding());
            String payload = "";

            try {
                payload = EncryptionUtil.decode(resp);
                if (payload == null) {
                    payload = resp;
                }
            } catch (Exception var5) {
            }

            logger.info(String.format("Response: REQUEST-TRACE-IDS=%s; current request id=%s;响应参数=%s", TraceId.get(), response.getId(), payload));
        } catch (Exception var6) {
            logger.warn("Failed to parse response payload", var6);
        }

    }

    private boolean pathEgnore(String path) {
        PatternMatcher pathMatcher = new AntPathMatcher();
        if (StringUtils.isEmpty(path)) {
            return false;
        } else {
            String[] var6;
            int var5 = (var6 = path_ignores).length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String type = var6[var4];
                if (pathMatcher.matches(type, path)) {
                    return true;
                }
            }

            return false;
        }
    }

    private boolean respEgnore(String contentType) {
        if (StringUtils.isEmpty(contentType)) {
            return false;
        } else {
            String[] var5;
            int var4 = (var5 = resp_ignores).length;

            for(int var3 = 0; var3 < var4; ++var3) {
                String type = var5[var3];
                if (contentType.toLowerCase().indexOf(type) != -1) {
                    return true;
                }
            }

            return false;
        }
    }

    private static boolean isMultipart(HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
    }

    private static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
