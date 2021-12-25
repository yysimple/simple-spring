package com.simple.simplespring.aop;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 23:35
 **/
public interface Pointcut {

    /**
     * 定义类匹配类，用于切点找到给定的接口和目标类。
     *
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * 方法匹配，找到表达式范围内匹配下的目标类和方法
     *
     * @return
     */
    MethodMatcher getMethodMatcher();


    /**
     * Canonical Pointcut instance that always matches.
     */
    Pointcut TRUE = TruePointcut.INSTANCE;
}
