package com.simple;

import com.simple.mapper.UserMapper;
import com.simple.service.UserService;
import com.simple.simplespring.beans.MutablePropertyValues;
import com.simple.simplespring.beans.PropertyValue;
import com.simple.simplespring.beans.PropertyValues;
import com.simple.simplespring.beans.factory.config.BeanDefinition;
import com.simple.simplespring.beans.factory.config.BeanReference;
import com.simple.simplespring.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 17:25
 **/
public class BeanTest {
    @Test
    public void testBeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userMapper", new BeanDefinition(UserMapper.class));

        // 3. UserService 设置属性[userId、userMapper]
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userMapper", new BeanReference("userMapper")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
