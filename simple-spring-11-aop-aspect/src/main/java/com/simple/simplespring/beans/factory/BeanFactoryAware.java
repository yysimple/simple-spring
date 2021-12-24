package com.simple.simplespring.beans.factory;

import com.simple.simplespring.beans.BeansException;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 10:11
 **/
public interface BeanFactoryAware {

    /**
     * 将该bean工厂传递
     * @param beanFactory
     * @throws BeansException
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
