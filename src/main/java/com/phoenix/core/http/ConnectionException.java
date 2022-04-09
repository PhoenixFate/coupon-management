package com.phoenix.core.http;

public class ConnectionException extends Exception {
    private static final long serialVersionUID = -2615370590441195647L;
    private boolean readTimedout = false;
    private int doneRetriedTimes = 0;

    public ConnectionException(String message, Throwable e) {
        super(message, e);
    }

    public ConnectionException(String message, Throwable e, int doneRetriedTimes) {
        super(message, e);
        this.doneRetriedTimes = doneRetriedTimes;
    }

    public ConnectionException(String message, Throwable e, boolean readTimedout) {
        super(message, e);
        this.readTimedout = readTimedout;
    }

    public boolean isReadTimedout() {
        return this.readTimedout;
    }

    public int getDoneRetriedTimes() {
        return this.doneRetriedTimes;
    }
}

