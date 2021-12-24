package com.simple.simplespring.aop.support;

import com.simple.simplespring.aop.PointcutAdvisor;
import com.simple.simplespring.util.ObjectUtils;
import com.sun.istack.internal.Nullable;
import org.aopalliance.aop.Advice;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 15:10
 **/
public abstract class AbstractPointcutAdvisor implements PointcutAdvisor {

    @Nullable
    private Integer order;


    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean isPerInstance() {
        return true;
    }


    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PointcutAdvisor)) {
            return false;
        }
        PointcutAdvisor otherAdvisor = (PointcutAdvisor) other;
        return (ObjectUtils.nullSafeEquals(getAdvice(), otherAdvisor.getAdvice()) &&
                ObjectUtils.nullSafeEquals(getPointcut(), otherAdvisor.getPointcut()));
    }

    @Override
    public int hashCode() {
        return PointcutAdvisor.class.hashCode();
    }
}
