package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.BeanUtils;
import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * 功能描述: 通过反射允许传入参数对构造器进行初始化
 *
 * @author: WuChengXing
 * @create: 2021-12-21 14:44
 **/
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    /**
     * spring 当中还对类定义里面的方法进行了处理，我们这里只通过构造器来进行处理
     *
     * @param beanDefinition
     * @param beanName
     * @param ctor
     * @param args
     * @return
     * @throws BeansException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        if (!Objects.isNull(ctor)) {
            return BeanUtils.instantiateClass(ctor, args);
        } else {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
