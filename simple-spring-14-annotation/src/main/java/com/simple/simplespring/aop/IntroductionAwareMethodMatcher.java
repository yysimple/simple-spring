package com.simple.simplespring.aop;

import java.lang.reflect.Method;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 23:53
 **/
public interface IntroductionAwareMethodMatcher extends MethodMatcher {
    /**
     * 匹配方法
     *
     * @param method
     * @param targetClass
     * @param hasIntroductions
     * @return
     */
    boolean matches(Method method, Class<?> targetClass, boolean hasIntroductions);
}
