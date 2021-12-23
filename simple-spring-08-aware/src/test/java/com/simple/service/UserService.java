package com.simple.service;

import com.simple.mapper.UserMapper;
import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.*;
import com.simple.simplespring.context.ApplicationContext;
import com.simple.simplespring.context.ApplicationContextAware;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 17:25
 **/
public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    private String userId;

    private String company;

    private String location;

    private UserMapper userMapper;

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    public String queryUserInfo() {
        System.out.println("查询用户信息：" + userMapper.queryUserName(userId));
        return userMapper.queryUserName(userId) + "," + company + "," + location;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is：" + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader：" + classLoader);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String uId) {
        this.userId = uId;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
