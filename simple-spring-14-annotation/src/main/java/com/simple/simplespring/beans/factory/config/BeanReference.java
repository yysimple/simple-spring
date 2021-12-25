package com.simple.simplespring.beans.factory.config;

/**
 * 功能描述: 这里其实就是将value进行了一层包装而已，通过name去拿到指定的对象，本来这里是一个接口的，为了简单，直接定义成一个类了
 * 在Spring中，存在几个实现：
 * - org.springframework.beans.factory.config.RuntimeBeanReference
 * - org.springframework.beans.factory.config.RuntimeBeanNameReference
 * - GroovyRuntimeBeanReference
 *
 * @author: WuChengXing
 * @create: 2021-12-21 16:42
 **/
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
