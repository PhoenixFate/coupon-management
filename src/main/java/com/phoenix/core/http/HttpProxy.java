package com.phoenix.core.http;


import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

public class HttpProxy {
    private String host;
    private int port;
    private String username;
    private String password;
    private boolean authenticationNeeded;

    public HttpProxy(String host, int port) {
        this.authenticationNeeded = false;
        this.host = host;
        this.port = port;
    }

    public HttpProxy(String host, int port, String username, String password) {
        this(host, port);
        this.username = username;
        this.password = password;
        this.authenticationNeeded = true;
    }

    public Proxy getNetProxy() {
        return new Proxy(Type.HTTP, new InetSocketAddress(this.host, this.port));
    }

    public boolean isAuthenticationNeeded() {
        return this.authenticationNeeded;
    }

    public String getProxyAuthorization() {
        return HttpUtils.getBasicAuthorization(this.username, this.password);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
