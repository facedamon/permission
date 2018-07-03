package com.facedamon.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @Authoe facedamon
 * @Description:
 * @Date: Credted in 下午10:39 2018/5/14
 * @Modified by:
 */
@Slf4j
public class PermissionException extends RuntimeException{
    public PermissionException() {
        super();
    }

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionException(Throwable cause) {
        super(cause);
    }

    protected PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
