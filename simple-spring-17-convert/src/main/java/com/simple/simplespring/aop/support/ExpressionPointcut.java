package com.simple.simplespring.aop.support;

import com.simple.simplespring.aop.Pointcut;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 23:47
 **/
public interface ExpressionPointcut extends Pointcut {
    /**
     * 获取切点表达式
     *
     * @return
     */
    @Nullable
    String getExpression();
}
