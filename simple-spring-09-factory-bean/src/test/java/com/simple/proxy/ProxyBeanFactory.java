package com.simple.proxy;

import com.simple.mapper.UserMapper;
import com.simple.simplespring.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 代理对象
 *
 * @author: WuChengXing
 * @create: 2021-12-23 12:13
 **/
public class ProxyBeanFactory implements FactoryBean<UserMapper> {
    @Override
    public UserMapper getObject() throws Exception {

        // 使用jdk的代理模式
        InvocationHandler handler = (proxy, method, args) -> {
            // 添加排除方法
            if ("toString".equals(method.getName())) return this.toString();

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "simple");
            hashMap.put("10002", "吴同学");
            hashMap.put("10003", "张同学");
            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };
        return (UserMapper) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserMapper.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return UserMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
