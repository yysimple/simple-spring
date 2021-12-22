package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.config.BeanDefinition;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Constructor;

/**
 * 功能描述: 实例化的策略
 *
 * @author: WuChengXing
 * @create: 2021-12-21 12:16
 **/
public interface InstantiationStrategy {

    /**
     * 模仿Spring中的实例化策略，不过Spring当中存在多种构造器去实例化bean
     *
     * @param beanDefinition
     * @param beanName
     * @param ctor
     * @param args
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, @Nullable String beanName, Constructor ctor, Object[] args) throws BeansException;
}
