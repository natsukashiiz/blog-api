package com.vv2dev.restfulapi.exception;

public class BaseException extends Exception {
    public BaseException(String code) {
        super(code);
    }
}
