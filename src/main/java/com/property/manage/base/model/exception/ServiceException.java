package com.property.manage.base.model.exception;


import com.property.manage.base.model.constants.ResponseCode;

/**
 * Created by guozhenbin on 17/2/20.
 */
public class ServiceException extends Exception{
    private int code = ResponseCode.CODE_API_INVOKE_ERROR;

    private String subCode;

    public ServiceException() {}

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, String subCode) {
        super(message);
        this.subCode = subCode;
    }

    public ServiceException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public ServiceException setCode(int code) {
        this.code = code;
        return this;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }
}
