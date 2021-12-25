package com.simple.simplespring.aop.framework.adapter;

import com.simple.simplespring.aop.BeforeAdvice;
import com.simple.simplespring.aop.MethodBeforeAdvice;
import com.simple.simplespring.util.Assert;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.io.Serializable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 15:05
 **/
public class MethodBeforeAdviceInterceptor implements MethodInterceptor, BeforeAdvice, Serializable {

    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor() {
    }

    /**
     * Create a new MethodBeforeAdviceInterceptor for the given advice.
     * @param advice the MethodBeforeAdvice to wrap
     */
    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        Assert.notNull(advice, "Advice must not be null");
        this.advice = advice;
    }


    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        this.advice.before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }

}
