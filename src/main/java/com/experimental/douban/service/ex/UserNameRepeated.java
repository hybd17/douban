package com.experimental.douban.service.ex;

public class UserNameRepeated extends ServiceException{
    public UserNameRepeated() {
        super();
    }

    public UserNameRepeated(String message) {
        super(message);
    }

    public UserNameRepeated(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameRepeated(Throwable cause) {
        super(cause);
    }

    protected UserNameRepeated(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
