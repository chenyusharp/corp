package com.xzy.sso.core.exception;

/**
 * @author xuxueli 2018-04-02 21:01:41
 */
public class XzySsoException extends RuntimeException {

    private static final long serialVersionUID = 42L;

    public XzySsoException(String msg) {
        super(msg);
    }

    public XzySsoException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public XzySsoException(Throwable cause) {
        super(cause);
    }

}
