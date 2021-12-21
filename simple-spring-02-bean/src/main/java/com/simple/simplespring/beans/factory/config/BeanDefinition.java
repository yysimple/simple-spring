package com.simple.simplespring.beans.factory.config;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-20 16:20
 **/
public class BeanDefinition {

    private Class beanClass;

    /**
     * Initialize the beanClass
     *
     * @param beanClass
     */
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
