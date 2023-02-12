package com.experimental.douban.service.ex;

public class SearchFailException extends ServiceException{
    public SearchFailException() {
        super();
    }

    public SearchFailException(String message) {
        super(message);
    }

    public SearchFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchFailException(Throwable cause) {
        super(cause);
    }

    protected SearchFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
