package com.simple.simplespring.beans.factory.config;

import com.simple.simplespring.beans.MutablePropertyValues;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述: 其实这里是接口，前面所有的章节里面因为偷懒，所以这里使用的是类
 * 从这个章节开始，打算再这里改造成接口，完全跟spring一样，这样才能更加读懂Spring
 *
 * @author: WuChengXing
 * @create: 2021-12-20 16:20
 **/
public interface BeanDefinition {

    /**
     * 获取bean的class类型，这里对应Spring中应该是去获取 bean的class名字，不是直接获取Class类型
     *
     * @return
     */
    Class getBeanClass();

    /**
     * 设置bean的class类型，Spring同上面一样，是名称
     *
     * @param beanClass
     */
    void setBeanClass(Class beanClass);

    /**
     * 获取对应的属性
     *
     * @return
     */
    MutablePropertyValues getPropertyValues();

    /**
     * 设置对应的属性
     *
     * @param propertyValues
     */
    void setPropertyValues(MutablePropertyValues propertyValues);

    /**
     * 设置初始化方法
     *
     * @param initMethodName
     */
    void setInitMethodName(@Nullable String initMethodName);

    /**
     * 返回初始化方法名称
     *
     * @return
     */
    @Nullable
    String getInitMethodName();

    /**
     * 设置销毁方法名
     *
     * @param destroyMethodName
     */
    void setDestroyMethodName(@Nullable String destroyMethodName);

    /**
     * 获取销毁方法名
     *
     * @return
     */
    @Nullable
    String getDestroyMethodName();

    /**
     * 判断属性是否为空
     *
     * @return
     */
    default boolean hasPropertyValues() {
        return !getPropertyValues().isEmpty();
    }
}
