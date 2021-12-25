package com.simple.simplespring.beans.factory.config;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.ConfigurableListableBeanFactory;

/**
 * 功能描述:Spring中也是一个函数式接口，算是对外暴露的端口，允许扩展
 *
 * @author: WuChengXing
 * @create: 2021-12-22 14:20
 **/
@FunctionalInterface
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
