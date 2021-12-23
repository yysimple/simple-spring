package com.simple.simplespring.beans.factory.config;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 10:25
 **/
public interface SingletonBeanRegistry {

    /**
     * 获取单例bean
     *
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

    /**
     * 注册单例bean
     *
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);
}
