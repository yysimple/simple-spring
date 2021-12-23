package com.simple.common;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.MutablePropertyValues;
import com.simple.simplespring.beans.PropertyValue;
import com.simple.simplespring.beans.factory.ConfigurableListableBeanFactory;
import com.simple.simplespring.beans.factory.config.BeanDefinition;
import com.simple.simplespring.beans.factory.config.BeanFactoryPostProcessor;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 17:27
 **/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
