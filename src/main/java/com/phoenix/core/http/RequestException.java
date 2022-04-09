package com.phoenix.core.http;


import com.alibaba.fastjson.JSON;
import com.phoenix.core.http.ResponseWrapper.ErrorObject;

public class RequestException extends Exception implements IRateLimiting {
    private static final long serialVersionUID = -3921022835186996212L;
    private final ResponseWrapper responseWrapper;

    public RequestException(ResponseWrapper responseWrapper) {
        super(responseWrapper.responseContent);
        this.responseWrapper = responseWrapper;
    }

    public int getStatus() {
        return this.responseWrapper.responseCode;
    }

    public long getMsgId() {
        ErrorObject eo = this.getErrorObject();
        return eo != null ? eo.msg_id : 0L;
    }

    public int getErrorCode() {
        ErrorObject eo = this.getErrorObject();
        return eo != null ? eo.error.code : -1;
    }

    public String getErrorMessage() {
        ErrorObject eo = this.getErrorObject();
        return eo != null ? eo.error.message : null;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

    private ErrorObject getErrorObject() {
        return this.responseWrapper.error;
    }

    public int getRateLimitQuota() {
        return this.responseWrapper.rateLimitQuota;
    }

    public int getRateLimitRemaining() {
        return this.responseWrapper.rateLimitRemaining;
    }

    public int getRateLimitReset() {
        return this.responseWrapper.rateLimitReset;
    }
}
