package com.simple.simplespring.aop;

import java.io.Serializable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 23:38
 **/
public class TrueClassFilter implements ClassFilter, Serializable {
    public static final TrueClassFilter INSTANCE = new TrueClassFilter();

    /**
     * Enforce Singleton pattern.
     */
    private TrueClassFilter() {
    }

    @Override
    public boolean matches(Class<?> clazz) {
        return true;
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
        return "ClassFilter.TRUE";
    }
}
