package com.phoenix.core.http;


import com.alibaba.fastjson.JSON;

public class ResponseWrapper {
    private static final int RESPONSE_CODE_NONE = -1;
    public int responseCode = -1;
    public String responseContent;
    public ResponseWrapper.ErrorObject error;
    public int rateLimitQuota;
    public int rateLimitRemaining;
    public int rateLimitReset;

    public ResponseWrapper() {
    }

    public void setRateLimit(String quota, String remaining, String reset) {
        if (quota != null) {
            try {
                this.rateLimitQuota = Integer.parseInt(quota);
                this.rateLimitRemaining = Integer.parseInt(remaining);
                this.rateLimitReset = Integer.parseInt(reset);
            } catch (NumberFormatException var5) {
            }

        }
    }

    public void setErrorObject() {
        this.error = (ResponseWrapper.ErrorObject)JSON.parseObject(this.responseContent, ResponseWrapper.ErrorObject.class);
    }

    public boolean isServerResponse() {
        if (this.responseCode == 200) {
            return true;
        } else {
            return this.responseCode > 0 && this.error != null && this.error.error.code > 0;
        }
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

    public class ErrorEntity {
        public int code;
        public String message;

        public ErrorEntity() {
        }

        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    public class ErrorObject {
        public long msg_id;
        public ResponseWrapper.ErrorEntity error;

        public ErrorObject() {
        }
    }
}
