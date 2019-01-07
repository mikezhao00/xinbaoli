package com.george.xinbaoli.exception;

public class XblException extends RuntimeException {
    public XblException(String msg)
    {
        super(msg);
    }

    public XblException(Throwable t) {
        super(t);
    }

    public XblException(String msg, Throwable t) {
        super(msg, t);
    }
}