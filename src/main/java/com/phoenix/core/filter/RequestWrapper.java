package com.phoenix.core.filter;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.input.TeeInputStream;
import org.springframework.mock.web.DelegatingServletInputStream;

public class RequestWrapper extends HttpServletRequestWrapper {
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private String id;
    private Date time = new Date();
    private boolean readed = false;

    public RequestWrapper(String requestId, HttpServletRequest request) {
        super(request);
        this.id = requestId;
    }

    public ServletInputStream getInputStream() throws IOException {
        if (this.readed) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.bos.toByteArray());
            return new DelegatingServletInputStream(byteArrayInputStream);
        } else {
            DelegatingServletInputStream stream = new DelegatingServletInputStream(new TeeInputStream(super.getInputStream(), this.bos));
            this.readed = true;
            return stream;
        }
    }

    public byte[] toByteArray() {
        return this.bos.toByteArray();
    }

    public String getId() {
        return this.id;
    }

    public Date getTime() {
        return this.time;
    }
}

