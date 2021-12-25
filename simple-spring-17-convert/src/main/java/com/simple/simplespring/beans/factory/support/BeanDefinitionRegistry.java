package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.BeansException;
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

    /**
     * 使用Bean名称查询BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的BeanDefinition
     *
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * Return the names of all beans defined in this registry.
     * <p>
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
