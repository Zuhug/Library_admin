package com.karkar.springboot.exception;

/**
 * 数据层的异常处理
 */
public class MapperException extends RuntimeException{

    private static final String  SELECT_ERROR_CODE = "101";
    private static final String  INSERT_ERROR_CODE = "102";
    private static final String  UPDATE_ERROR_CODE = "103";
    private static final String  DELETE_ERROR_CODE = "104";

    private String code;

    public MapperException(String msg) {
        super(msg);
    }

    public MapperException(String msg, String code) {
        this(msg);
        this.code = code;
    }

}
