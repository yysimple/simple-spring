package com.simple.simplespring.core;

import com.sun.istack.internal.Nullable;

import java.io.IOException;

/**
 * 功能描述: 这里其实是有点迷惑的，为什么无效uri要派抛出一个嵌套异常
 *
 * @author: WuChengXing
 * @create: 2021-12-22 00:06
 **/
public class NestedIOException extends IOException {

    static {
        // Eagerly load the NestedExceptionUtils class to avoid classloader deadlock
        // issues on OSGi when calling getMessage(). Reported by Don Brown; SPR-5607.
        NestedExceptionUtils.class.getName();
    }


    /**
     * Construct a {@code NestedIOException} with the specified detail message.
     *
     * @param msg the detail message
     */
    public NestedIOException(String msg) {
        super(msg);
    }

    /**
     * Construct a {@code NestedIOException} with the specified detail message
     * and nested exception.
     *
     * @param msg   the detail message
     * @param cause the nested exception
     */
    public NestedIOException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }


    /**
     * Return the detail message, including the message from the nested exception
     * if there is one.
     */
    @Override
    @Nullable
    public String getMessage() {
        return NestedExceptionUtils.buildMessage(super.getMessage(), getCause());
    }

}
