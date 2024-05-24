package com.eptison.exception;

/**
 * 其他未捕获异常
 * @author 朱智贤
 * @since 2019-08-01
 */
public class EpUnexpectedException extends RuntimeException{

    public EpUnexpectedException() {
    }

    public EpUnexpectedException(String message) {
        super(message);
    }

    public EpUnexpectedException(Throwable cause) {
        super(cause);
    }
}
