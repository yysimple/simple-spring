package com.simple.simplespring.beans;

import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 10:46
 **/
public abstract class BeansException extends RuntimeException {

    /**
     * Create a new BeansException with the specified message.
     * @param msg the detail message
     */
    public BeansException(String msg) {
        super(msg);
    }

    /**
     * Create a new BeansException with the specified message
     * and root cause.
     * @param msg the detail message
     * @param cause the root cause
     */
    public BeansException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }
}
