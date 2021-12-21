package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.BeanDefinitionStoreException;
import com.simple.simplespring.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述: 这个类中包含了几乎所有对bean的定义信息的操作方法，包括注册删除等功能
 *
 * @author: WuChengXing
 * @create: 2021-12-21 11:08
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    /**
     * 这里也是完全仿造spring做的容器以及大小
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    /**
     * 从注册缓存中拿到bean定义信息；
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        synchronized (this.beanDefinitionMap) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition == null) {
                throw new BeanDefinitionStoreException("No bean named '" + beanName + "' is defined");
            }
            return beanDefinition;
        }
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException {
        synchronized (this.beanDefinitionMap) {
            this.beanDefinitionMap.put(beanName, beanDefinition);
        }
    }
}
