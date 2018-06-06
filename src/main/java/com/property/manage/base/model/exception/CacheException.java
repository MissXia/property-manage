package com.property.manage.base.model.exception;

/**
 * Created by guozhenbin on 17/2/17.
 */
public class CacheException extends RuntimeException {
    public CacheException() {
    }

    public CacheException(String s) {
        super(s);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }
}
