package com.property.manage.base.model.exception;


import com.property.manage.base.model.constants.ResponseCode;

public class SessionException extends Exception{
    //默认当前session没有用户信息
    private int code = ResponseCode.CODE_NO_USER_ERROR;

    public SessionException() {}

    public SessionException(String message) {
        super(message);
    }

    public SessionException(String message, int code) {
        super(message);
        this.code = code;
    }

    public SessionException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public SessionException setCode(int code) {
        this.code = code;
        return this;
    }
}