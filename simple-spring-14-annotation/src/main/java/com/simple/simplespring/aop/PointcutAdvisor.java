package com.simple.simplespring.aop;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 14:55
 **/
public interface PointcutAdvisor extends Advisor {

    /**
     * Get the Pointcut that drives this advisor.
     *
     * @return
     */
    Pointcut getPointcut();

}
