package com.simple.simplespring.beans.factory.config;

import com.simple.simplespring.beans.MutablePropertyValues;
import com.simple.simplespring.beans.PropertyValues;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-20 16:20
 **/
public class BeanDefinition {

    private Class beanClass;

    private PropertyValues propertyValues;

    /**
     * Initialize the beanClass
     *
     * @param beanClass
     */
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new MutablePropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public boolean hasPropertyValues() {
        return (this.propertyValues != null && !this.propertyValues.isEmpty());
    }
}
