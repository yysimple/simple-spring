package com.simple.simplespring.beans.factory;

import com.simple.simplespring.beans.BeansException;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-20 16:24
 **/
public interface BeanFactory {

    /**
     * Access to the bean
     *
     * @param name
     * @return
     */
    Object getBean(String name);

    /**
     * 可以传递参数的bean
     *
     * @param name
     * @param args
     * @return
     */
    Object getBean(String name, Object... args);

    /**
     * 通过类型去获取bean
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    /**
     * 根据类型获取到对应的bean
     *
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;
}
