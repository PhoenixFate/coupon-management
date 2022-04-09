package com.phoenix.core.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.mock.web.DelegatingServletOutputStream;

public class ResponseWrapper extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private PrintWriter writer;
    private String id;

    public ResponseWrapper(String requestId, HttpServletResponse response) {
        super(response);
        this.writer = new PrintWriter(this.bos);
        this.id = requestId;
    }

    public ServletResponse getResponse() {
        return super.getResponse();
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return new DelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), this.bos));
    }

    public PrintWriter getWriter() throws IOException {
        return new TeePrintWriter(super.getWriter(), this.writer);
    }

    public byte[] toByteArray() {
        return this.bos.toByteArray();
    }

    public String getId() {
        return this.id;
    }
}

