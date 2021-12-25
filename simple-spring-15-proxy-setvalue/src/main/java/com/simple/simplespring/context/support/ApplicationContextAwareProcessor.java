package com.simple.simplespring.context.support;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.config.BeanPostProcessor;
import com.simple.simplespring.context.ApplicationContext;
import com.simple.simplespring.context.ApplicationContextAware;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 10:44
 **/
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
