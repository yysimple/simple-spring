package com.simple.simplespring.aop.support;

import org.aopalliance.aop.Advice;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 15:11
 **/
public abstract class AbstractGenericPointcutAdvisor extends AbstractPointcutAdvisor {

    private Advice advice = EMPTY_ADVICE;

    /**
     * Specify the advice that this advisor should apply.
     */
    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }


    @Override
    public String toString() {
        return getClass().getName() + ": advice [" + getAdvice() + "]";
    }
}
