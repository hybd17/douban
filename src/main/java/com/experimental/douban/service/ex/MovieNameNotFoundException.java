package com.experimental.douban.service.ex;

public class MovieNameNotFoundException extends ServiceException{
    public MovieNameNotFoundException() {
        super();
    }

    public MovieNameNotFoundException(String message) {
        super(message);
    }

    public MovieNameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieNameNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MovieNameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
