package com.simple.simplespring.aop;

import java.io.Serializable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 23:39
 **/
public class TruePointcut implements Pointcut, Serializable {

    public static final TruePointcut INSTANCE = new TruePointcut();

    /**
     * Enforce Singleton pattern.
     */
    private TruePointcut() {
    }

    @Override
    public ClassFilter getClassFilter() {
        return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return MethodMatcher.TRUE;
    }

    /**
     * Required to support serialization. Replaces with canonical
     * instance on deserialization, protecting Singleton pattern.
     * Alternative to overriding {@code equals()}.
     */
    private Object readResolve() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Pointcut.TRUE";
    }
}
