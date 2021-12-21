package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.factory.BeanDefinitionStoreException;
import com.simple.simplespring.beans.factory.config.BeanDefinition;

/**
 * 功能描述: bean信息注册的接口
 *
 * @author: WuChengXing
 * @create: 2021-12-21 11:09
 **/
public interface BeanDefinitionRegistry {

    /**
     * 注册bean信息
     *
     * @param beanName
     * @param beanDefinition
     * @throws BeanDefinitionStoreException
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException;
}
