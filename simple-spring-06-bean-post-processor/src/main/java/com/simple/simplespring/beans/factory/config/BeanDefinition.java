package com.simple.simplespring.beans.factory.config;

import com.simple.simplespring.beans.MutablePropertyValues;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-20 16:20
 **/
public class BeanDefinition {

    private Class beanClass;

    private MutablePropertyValues propertyValues;

    /**
     * Initialize the beanClass
     *
     * @param beanClass
     */
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new MutablePropertyValues();
    }

    public BeanDefinition(Class beanClass, MutablePropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new MutablePropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public MutablePropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public boolean hasPropertyValues() {
        return (this.propertyValues != null && !this.propertyValues.isEmpty());
    }
}
