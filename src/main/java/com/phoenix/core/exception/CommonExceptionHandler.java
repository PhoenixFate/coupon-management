package com.phoenix.core.exception;

import com.phoenix.core.common.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public CommonExceptionHandler() {
    }

    @ExceptionHandler({Exception.class})
    public BaseResponse handleException(Exception e) {
        this.logger.error(e.getMessage(), e);
        return new BaseResponse("-1", "服务器错误，请联系管理员");
    }
}