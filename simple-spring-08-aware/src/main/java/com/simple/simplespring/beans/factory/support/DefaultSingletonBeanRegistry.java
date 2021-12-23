package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.factory.BeanCreationException;
import com.simple.simplespring.beans.factory.DisposableBean;
import com.simple.simplespring.beans.factory.config.SingletonBeanRegistry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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

    /**
     * 用来存放销毁bean的那些定义
     */
    private final Map<String, Object> disposableBeans = new LinkedHashMap<>();

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

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = (DisposableBean) this.disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeanCreationException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
