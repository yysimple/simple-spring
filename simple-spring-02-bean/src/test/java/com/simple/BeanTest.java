package com.simple;

import com.simple.service.UserService;
import com.simple.simplespring.beans.factory.BeanDefinitionStoreException;
import com.simple.simplespring.beans.factory.config.BeanDefinition;
import com.simple.simplespring.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 11:19
 **/
public class BeanTest {

    @Test
    public void getBeanTest() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        String beanName = "userService";
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition(beanName, beanDefinition);

        /**
         * 查询用户信息1640057124032
         * 查询用户信息1640057124032
         * 两次执行结果是一样的，证明是单例的
         */
        UserService one = (UserService) beanFactory.getBean(beanName);
        one.queryUserInfo();

        UserService two = (UserService) beanFactory.getBean(beanName);
        two.queryUserInfo();
        try {
            UserService notExist = (UserService) beanFactory.getBean("test");
            notExist.queryUserInfo();
        } catch (BeanDefinitionStoreException e) {
            System.out.println(e.getBeanName() + ": " + e.getResourceDescription());
        }

        // 从这里能看到，只能注册一个bean，因为是单例模式
        try {
            beanFactory.registerSingleton(beanName, beanDefinition);
        } catch (IllegalStateException e) {
            // Could not register object [com.simple.simplespring.beans.factory.config.BeanDefinition@2b71fc7e] under bean name 'userService':
            // there is already object [com.simple.service.UserService@5ce65a89] bound
            System.out.println(e.getMessage());
        }

    }
}
