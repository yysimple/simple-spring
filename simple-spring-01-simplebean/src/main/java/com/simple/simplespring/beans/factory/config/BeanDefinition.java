package com.simple.simplespring.beans.factory.config;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-20 16:20
 **/
public class BeanDefinition {

    private final Object bean;

    /**
     * Initialize the bean
     *
     * @param bean
     */
    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
