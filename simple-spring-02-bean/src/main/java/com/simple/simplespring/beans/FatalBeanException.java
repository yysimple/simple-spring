package com.simple.simplespring.beans;

import com.simple.simplespring.beans.BeansException;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 10:49
 **/
public class FatalBeanException extends BeansException {
    /**
     * Create a new FatalBeanException with the specified message.
     *
     * @param msg the detail message
     */
    public FatalBeanException(String msg) {
        super(msg);
    }

    /**
     * Create a new FatalBeanException with the specified message
     * and root cause.
     *
     * @param msg   the detail message
     * @param cause the root cause
     */
    public FatalBeanException(String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }
}
