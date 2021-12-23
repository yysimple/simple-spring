package com.simple.simplespring.beans;

import com.sun.istack.internal.Nullable;

import java.util.Iterator;

/**
 * 功能描述: 属性集合，这个在Spring中其实是个接口，而其对应的子实现有挺多 我们这里只实现一种，而且方法不会实现很多
 *
 * @author: WuChengXing
 * @create: 2021-12-21 16:18
 **/
public interface PropertyValues extends Iterable<PropertyValue> {

    /**
     * 获取所有的属性
     *
     * @return
     */
    PropertyValue[] getPropertyValues();

    /**
     * 通过属性名拿到属性值
     *
     * @param propertyName
     * @return
     */
    @Nullable
    PropertyValue getPropertyValue(String propertyName);

    /**
     * 是否包含该属性名
     *
     * @param propertyName
     * @return
     */
    boolean contains(String propertyName);

    /**
     * 判断属性是否为空
     *
     * @return
     */
    boolean isEmpty();

    @Override
    default Iterator<PropertyValue> iterator() {
        return null;
    }
}
