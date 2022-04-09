package com.phoenix.core.exception;


public class ServerException extends Exception {
    private static final long serialVersionUID = -6787224054565496673L;
    private String expCode;
    private String expMsg;

    public ServerException(String expCode) {
        this.expCode = expCode;
        this.expMsg = ServiceMsg.getRspMsg(expCode);
    }

    public ServerException(String expCode, String expMsg) {
        this.expCode = expCode;
        this.expMsg = expMsg;
    }

    public String getExpCode() {
        return this.expCode;
    }

    public void setExpCode(String expCode) {
        this.expCode = expCode;
    }

    public String getExpMsg() {
        return this.expMsg;
    }

    public void setExpMsg(String expMsg) {
        this.expMsg = expMsg;
    }
}
