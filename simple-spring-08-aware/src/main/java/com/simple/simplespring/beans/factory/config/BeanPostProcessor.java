package com.simple.simplespring.beans.factory.config;

import com.simple.simplespring.beans.BeansException;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 14:25
 **/
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前执行该方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Nullable
    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
