package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.BeanCreationException;
import com.simple.simplespring.beans.factory.config.BeanDefinition;

/**
 * 功能描述: 这个类从spring的继承关系来看，这里只是负责去实现创建bean的功能，没有去实现获取bean的定义信息
 *
 * @author: WuChengXing
 * @create: 2021-12-21 10:59
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * spring中这里的实现非常复杂，可以理解分成 拿到类加载器、然后进行属性填充、然后进行bean的实例化
     * 这里简单实现，根据bean定义，然后直接通过反射拿到构造器信息进行初始化，然后添加到缓存中去
     *
     * @param beanName
     * @param beanDefinition
     * @return
     * @throws BeanCreationException
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanCreationException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanCreationException("Creation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
