package com.karkar.springboot.exception;

/**
 * 业务层的异常处理
 */
public class ServiceException extends RuntimeException{

    private String code;
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        this(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
