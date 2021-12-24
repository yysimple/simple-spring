package com.simple.simplespring.aop;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 23:37
 **/
@FunctionalInterface
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     *
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);


    /**
     * Canonical instance of a ClassFilter that matches all classes.
     */
    ClassFilter TRUE = TrueClassFilter.INSTANCE;
}
