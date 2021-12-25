package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.factory.BeanCreationException;
import com.simple.simplespring.beans.factory.DisposableBean;
import com.simple.simplespring.beans.factory.ObjectFactory;
import com.simple.simplespring.beans.factory.config.SingletonBeanRegistry;
import com.simple.simplespring.util.Assert;
import com.sun.istack.internal.Nullable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 10:28
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * Cache of singleton objects: bean name to bean instance.
     * 一级缓存，存放普通对象
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    /**
     * Cache of early singleton objects: bean name to bean instance.
     * 二级缓存，提前暴漏对象，没有完全实例化的对象
     */
    private final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(16);

    /**
     * Cache of singleton factories: bean name to ObjectFactory.
     * 三级缓存，存放代理对象
     */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);

    /**
     * Set of registered singletons, containing the bean names in registration order.
     * 注册的bean都会存在这里面，可以用于统计、排序
     */
    private final Set<String> registeredSingletons = new LinkedHashSet<>(256);

    /**
     * Names of beans that are currently in creation.
     */
    private final Set<String> singletonsCurrentlyInCreation =
            Collections.newSetFromMap(new ConcurrentHashMap<>(16));

    /**
     * 用来存放销毁bean的那些定义
     */
    private final Map<String, Object> disposableBeans = new LinkedHashMap<>();

    protected static final Object NULL_OBJECT = new Object();

    @Override
    public Object getSingleton(String beanName) {
        return getSingleton(beanName, true);
    }

    @Nullable
    protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        // Quick check for existing instance without full singleton lock
        Object singletonObject = this.singletonObjects.get(beanName);
        // 这里本来还会有个是否是当前对象的判断，这里就不判断了 && isSingletonCurrentlyInCreation(beanName)
        // 一级缓存中不存在，判断二级缓存中是否存在对象 todo
        if (singletonObject == null) {
            singletonObject = this.earlySingletonObjects.get(beanName);
            // 二级缓存中不存在，则判断其是否允许从二级缓存中拿取（二级缓存）todo
            if (singletonObject == null && allowEarlyReference) {
                // 这里不再是快速检查，而是上锁来从新判断
                synchronized (this.singletonObjects) {
                    // Consistent creation of early reference within full singleton lock
                    singletonObject = this.singletonObjects.get(beanName);
                    if (singletonObject == null) {
                        singletonObject = this.earlySingletonObjects.get(beanName);
                        if (singletonObject == null) {
                            // 二级缓存为空的情况下，从三级缓存中取获取
                            ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
                            if (singletonFactory != null) {
                                // 不为空的情况下，拿到三级缓存
                                singletonObject = singletonFactory.getObject();
                                // 这里会将三级缓存中的数据拿出来放到二级缓存中，然后将三级缓存中的对象移除
                                this.earlySingletonObjects.put(beanName, singletonObject);
                                this.singletonFactories.remove(beanName);
                            }
                        }
                    }
                }
            }
        }
        return singletonObject;
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
        // 添加单例对象的时候，会加到一级缓存中去，会将二、三及缓存中可能存在的对象移除
        synchronized (this.singletonObjects) {
            this.singletonObjects.put(beanName, singletonObject);
            this.singletonFactories.remove(beanName);
            this.earlySingletonObjects.remove(beanName);
            this.registeredSingletons.add(beanName);
        }
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        Assert.notNull(singletonFactory, "Singleton factory must not be null");
        // 添加代理对象，也即三级缓存中的对象的时候，会优先存入一级缓存中
        synchronized (this.singletonObjects) {
            if (!this.singletonObjects.containsKey(beanName)) {
                this.singletonFactories.put(beanName, singletonFactory);
                this.earlySingletonObjects.remove(beanName);
                this.registeredSingletons.add(beanName);
            }
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
