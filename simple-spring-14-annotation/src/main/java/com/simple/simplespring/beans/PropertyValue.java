package com.simple.simplespring.beans;

import com.simple.simplespring.util.Assert;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 16:16
 **/
public class PropertyValue {

    private final String name;

    @Nullable
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public PropertyValue(PropertyValue original) {
        Assert.notNull(original, "Original must not be null");
        this.name = original.getName();
        this.value = original.getValue();
        // 其实还有很多属性的初始化，这里就简单点，只要名字和属性
        // 比如下面两个方法，就是讲一些值放到另外的类中进行保存，用来做其他的操作，我们这里就不做了
        /* setSource(original.getSource());
        copyAttributesFrom(original);*/
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
