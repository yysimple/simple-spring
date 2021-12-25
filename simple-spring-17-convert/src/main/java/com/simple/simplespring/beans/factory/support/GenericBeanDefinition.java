package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.MutablePropertyValues;
import com.simple.simplespring.util.ObjectUtils;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述: 这个对应的也是 BeanDefinition 的实现，这里就模拟一下，使得其可以 new
 * 所以这里就初始化构造器即可，然后具体实现就调用父类的实现；
 * <p>
 * 抽象类不能实例化的原因：因为其算是一个不完整的类，也即有一些行为是没有去实现的，所以再java约定中
 * 我们不能直接去实例化一个抽象类，也即该对象是无法在对上分配空间的，只能子类隐式的调用
 *
 * @author: WuChengXing
 * @create: 2021-12-22 22:24
 **/
public class GenericBeanDefinition extends AbstractBeanDefinition {

    public GenericBeanDefinition(Class beanClass) {
        super(beanClass);
    }

    public GenericBeanDefinition(Class beanClass, MutablePropertyValues propertyValues) {
        super(beanClass, propertyValues);
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GenericBeanDefinition)) {
            return false;
        }
        GenericBeanDefinition that = (GenericBeanDefinition) other;
        return (ObjectUtils.nullSafeEquals(this.getBeanClass().getConstructors(), that.getBeanClass().getConstructors()) && super.equals(other));
    }
}
