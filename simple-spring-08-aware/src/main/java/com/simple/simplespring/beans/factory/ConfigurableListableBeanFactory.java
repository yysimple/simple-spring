package com.simple.simplespring.beans.factory;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.config.AutowireCapableBeanFactory;
import com.simple.simplespring.beans.factory.config.BeanDefinition;
import com.simple.simplespring.beans.factory.config.BeanPostProcessor;
import com.simple.simplespring.beans.factory.config.ConfigurableBeanFactory;

/**
 * 功能描述: 之前三个类都是对BeanFactory的增强，那么这个类更不用说了，强中之强
 * 他提供了巨多的对于BeanFactory的扩展
 *
 * @author: WuChengXing
 * @create: 2021-12-22 10:49
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 就是简单的获取bean的定义信息
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化bean
     *
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;

    /**
     * 添加对应的 BeanPostProcessor 类，进行bean实例化的前后的处理
     * @param beanPostProcessor
     */
    @Override
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
