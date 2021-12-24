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

    public static final String SCOPE_DEFAULT = "";

    @Nullable
    private String scope = SCOPE_DEFAULT;

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

    /**
     * Set the name of the target scope for the bean.
     * <p>The default is singleton status, although this is only applied once
     * a bean definition becomes active in the containing factory. A bean
     * definition may eventually inherit its scope from a parent bean definition.
     * For this reason, the default scope name is an empty string (i.e., {@code ""}),
     * with singleton status being assumed until a resolved scope is set.
     * @see #SCOPE_SINGLETON
     * @see #SCOPE_PROTOTYPE
     */
    @Override
    public void setScope(@Nullable String scope) {
        this.scope = scope;
    }

    /**
     * Return the name of the target scope for the bean.
     */
    @Override
    @Nullable
    public String getScope() {
        return this.scope;
    }

    /**
     * Return whether this a <b>Singleton</b>, with a single shared instance
     * returned from all calls.
     * @see #SCOPE_SINGLETON
     */
    @Override
    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(this.scope) || SCOPE_DEFAULT.equals(this.scope);
    }

    /**
     * Return whether this a <b>Prototype</b>, with an independent instance
     * returned for each call.
     * @see #SCOPE_PROTOTYPE
     */
    @Override
    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(this.scope);
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
