package com.simple.simplespring.aop.aspectj;

import com.simple.simplespring.aop.Pointcut;
import com.simple.simplespring.aop.support.AbstractGenericPointcutAdvisor;
import com.simple.simplespring.beans.factory.BeanFactory;
import com.simple.simplespring.beans.factory.BeanFactoryAware;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述: 这个算是一个最终的组合，包含了 切点表达式，通知等
 *
 * @author: WuChengXing
 * @create: 2021-12-24 15:09
 **/
public class AspectJExpressionPointcutAdvisor extends AbstractGenericPointcutAdvisor {

    /**
     * spring在此会直接new一个 AspectJExpressionPointcut
     */
    private AspectJExpressionPointcut pointcut;

    /**
     * 表达式
     */
    private String expression;

    public void setExpression(@Nullable String expression) {
        this.expression = expression;
    }

    @Nullable
    public String getExpression() {
        return this.expression;
    }

    public void setLocation(@Nullable String location) {
        this.pointcut.setLocation(location);
    }

    @Nullable
    public String getLocation() {
        return this.pointcut.getLocation();
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
