package com.simple.simplespring.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.PropertyValues;
import com.simple.simplespring.beans.factory.BeanFactory;
import com.simple.simplespring.beans.factory.BeanFactoryAware;
import com.simple.simplespring.beans.factory.ConfigurableListableBeanFactory;
import com.simple.simplespring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.simple.simplespring.util.ClassUtils;

import java.lang.reflect.Field;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 08:47
 **/
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 1. 处理注解 @Value
        Class<?> clazz = bean.getClass();
        // 判断其使用哪种代理方式能获取到bean类型信息
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;

        // 拿到对应类的所有字段
        Field[] declaredFields = clazz.getDeclaredFields();

        // 遍历处理加上了@Value注解的字段
        for (Field field : declaredFields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation) {
                // 拿到字段里面的值
                String value = valueAnnotation.value();
                // 解析，拿到实际值
                value = beanFactory.resolveEmbeddedValue(value);
                // 为该字段设置值
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }

        // 2. 处理注解 @Autowired
        for (Field field : declaredFields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation) {
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                // 需要配合 Qualifier 一起为其取别名
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (null != qualifierAnnotation) {
                    dependentBeanName = qualifierAnnotation.value();
                    // 存在别名就以别名 + 类型取获取bean
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }

        return pvs;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return false;
    }
}
