package com.simple.common;

import com.simple.service.UserService;
import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.config.BeanPostProcessor;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 17:26
 **/
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在这里其实 userService 已经可以用了
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
