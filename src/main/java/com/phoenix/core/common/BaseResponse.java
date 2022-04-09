package com.phoenix.core.common;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;

public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 7606166870506307516L;
    private String rspCode = "1";
    private String rspMsg = "";
    private Object rspData = new JSONObject();

    public BaseResponse() {
    }

    public BaseResponse(Object result) {
        this.rspData = result;
    }

    public BaseResponse(String statusCode, Object result) {
        this.rspCode = statusCode;
        this.rspData = result;
    }

    public BaseResponse(String statusCode, String msg) {
        this.rspCode = statusCode;
        this.rspMsg = msg;
    }

    public BaseResponse(String statusCode, String msg, boolean flag) {
        this.rspCode = statusCode;
        this.rspMsg = msg;
    }

    public String getRspCode() {
        return this.rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() {
        return this.rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    public Object getRspData() {
        return this.rspData;
    }

    public void setRspData(Object rspData) {
        this.rspData = rspData;
    }

    public static long getSerialversionuid() {
        return 7606166870506307516L;
    }
}
