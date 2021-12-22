package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.BeanMetadataAttributeAccessor;
import com.simple.simplespring.beans.MutablePropertyValues;
import com.simple.simplespring.beans.factory.config.BeanDefinition;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 18:44
 **/
public abstract class AbstractBeanDefinition extends BeanMetadataAttributeAccessor
        implements BeanDefinition, Cloneable {

    private Class beanClass;

    private MutablePropertyValues propertyValues;

    @Nullable
    private String initMethodName;

    @Nullable
    private String destroyMethodName;

    public AbstractBeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new MutablePropertyValues();
    }

    public AbstractBeanDefinition(Class beanClass, MutablePropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new MutablePropertyValues();
    }

    @Override
    public Class getBeanClass() {
        return beanClass;
    }

    @Override
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public MutablePropertyValues getPropertyValues() {
        return propertyValues;
    }

    @Override
    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    @Override
    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    @Override
    public String getInitMethodName() {
        return this.initMethodName;
    }

    @Override
    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    @Override
    public String getDestroyMethodName() {
        return this.destroyMethodName;
    }

    @Override
    public boolean hasPropertyValues() {
        return (this.propertyValues != null && !this.propertyValues.isEmpty());
    }
}
