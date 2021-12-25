package com.simple.simplespring.aop;

import com.sun.istack.internal.Nullable;

import java.lang.reflect.Method;

/**
 * 功能描述: 切面前置通知
 *
 * @author: WuChengXing
 * @create: 2021-12-24 15:00
 **/
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * Callback before a given method is invoked.
     *
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    void before(Method method, Object[] args, @Nullable Object target) throws Throwable;
}
