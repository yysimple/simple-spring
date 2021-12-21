package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 10:28
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * 这里的容量大小也是模仿spring来设置的
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) throws IllegalStateException {
        synchronized (this.singletonObjects) {
            Object oldObject = this.singletonObjects.get(beanName);
            // 完全模仿spring，当该bean存在的时候，则抛出非法逻辑异常
            if (oldObject != null) {
                throw new IllegalStateException("Could not register object [" + singletonObject +
                        "] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
            }
            // 否则的话，这里去添加指定的bean
            addSingleton(beanName, singletonObject);
        }
    }

    public void addSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonObjects) {
            this.singletonObjects.put(beanName, singletonObject);
        }
    }
}
