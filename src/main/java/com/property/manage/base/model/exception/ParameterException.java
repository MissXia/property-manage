package com.property.manage.base.model.exception;

import com.alibaba.fastjson.JSON;
import com.property.manage.base.model.constants.ResponseCode;


import java.text.MessageFormat;

public class ParameterException extends Exception {

    private int code = ResponseCode.CODE_SERVICE_WEB_PARAMS_ERROR;

    private String fields;

    private String subCode;

    public ParameterException() {
    }

    public ParameterException(String message, Object obj) {
        super(message);
        if (obj != null) {
            if (obj instanceof String) this.fields = obj.toString();
            else this.fields = JSON.toJSONString(obj);
        }
    }

    public ParameterException(String message, String subCode) {
        super(message);
        this.subCode = subCode;
    }

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }

    public ParameterException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ParameterException(String message, int code, Object... args) {
        super(MessageFormat.format(message, args));
        this.code = code;
    }

    public ParameterException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public ParameterException setCode(int code) {
        this.code = code;
        return this;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }
}
