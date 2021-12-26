package com.simple.simplespring.beans;

import com.sun.istack.internal.Nullable;

import java.io.Serializable;
import java.util.*;

/**
 * 功能描述: 这个算是其实现之一，为了和spring保持一致，我们这里实现一个简易版的
 *
 * @author: WuChengXing
 * @create: 2021-12-21 16:20
 **/
public class MutablePropertyValues implements PropertyValues, Serializable {

    /**
     * 这里的话是把Spring对该集合初始化的操作全部放在MutablePropertyValues的构造器中，提供了多种
     */
    private final Set<PropertyValue> propertyValueList;

    public MutablePropertyValues() {
        this.propertyValueList = new HashSet<>(0);
    }

    /**
     * 通过map属性来实现添加，属性名key，属性值为value
     *
     * @param original
     */
    public MutablePropertyValues(@Nullable Map<?, ?> original) {
        if (original != null) {
            this.propertyValueList = new HashSet<>(original.size());
            original.forEach((attrName, attrValue) -> this.propertyValueList.add(
                    new PropertyValue(attrName.toString(), attrValue)));
        } else {
            this.propertyValueList = new HashSet<>(0);
        }
    }

    public MutablePropertyValues(@Nullable PropertyValues original) {
        if (original != null) {
            PropertyValue[] pvs = original.getPropertyValues();
            this.propertyValueList = new HashSet<>(pvs.length);
            for (PropertyValue pv : pvs) {
                this.propertyValueList.add(new PropertyValue(pv));
            }
        } else {
            this.propertyValueList = new HashSet<>(0);
        }
    }

    public MutablePropertyValues(@Nullable Set<PropertyValue> propertyValueList) {
        this.propertyValueList =
                (propertyValueList != null ? propertyValueList : new HashSet<>());
    }

    /**
     * 通过直接添加 传入PropertyValue 来实现添加
     *
     * @param pv
     */
    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    /**
     * 支持批量添加属性
     *
     * @param other
     * @return
     */
    public MutablePropertyValues addPropertyValues(@Nullable PropertyValues other) {
        if (other != null) {
            PropertyValue[] pvs = other.getPropertyValues();
            for (PropertyValue pv : pvs) {
                addPropertyValue(new PropertyValue(pv));
            }
        }
        return this;
    }

    /**
     * 直接传入属性名和属性值的初始化方式
     *
     * @param propertyName
     * @param propertyValue
     */
    public void addPropertyValue(String propertyName, Object propertyValue) {
        addPropertyValue(new PropertyValue(propertyName, propertyValue));
    }

    /**
     * map 作为值的传输方式
     *
     * @param other
     * @return
     */
    public MutablePropertyValues addPropertyValues(@Nullable Map<?, ?> other) {
        if (other != null) {
            other.forEach((attrName, attrValue) -> addPropertyValue(
                    new PropertyValue(attrName.toString(), attrValue)));
        }
        return this;
    }

    @Override
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    @Override
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }

    @Override
    public boolean contains(String propertyName) {
        return !Objects.isNull(getPropertyValue(propertyName));
    }

    @Override
    public boolean isEmpty() {
        return propertyValueList.size() <= 0;
    }
}
