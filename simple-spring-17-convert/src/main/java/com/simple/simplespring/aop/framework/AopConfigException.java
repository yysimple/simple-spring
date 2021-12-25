package com.simple.simplespring.aop.framework;

import com.simple.simplespring.core.NestedRuntimeException;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 10:08
 **/
public class AopConfigException extends NestedRuntimeException {
    /**
     * Constructor for AopConfigException.
     *
     * @param msg the detail message
     */
    public AopConfigException(String msg) {
        super(msg);
    }

    /**
     * Constructor for AopConfigException.
     *
     * @param msg   the detail message
     * @param cause the root cause
     */
    public AopConfigException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
