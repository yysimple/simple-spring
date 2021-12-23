package com.simple.simplespring.context.event;

import com.simple.simplespring.beans.factory.BeanClassLoaderAware;
import com.simple.simplespring.beans.factory.BeanCreationException;
import com.simple.simplespring.beans.factory.BeanFactory;
import com.simple.simplespring.beans.factory.BeanFactoryAware;
import com.simple.simplespring.beans.factory.config.ConfigurableBeanFactory;
import com.simple.simplespring.context.ApplicationEvent;
import com.simple.simplespring.context.ApplicationListener;
import com.simple.simplespring.util.ClassUtils;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 15:31
 **/
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanClassLoaderAware, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public final void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 这个就是来注册所有事件，到时候可以进行广播
     *
     * @param event
     * @return
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            // 这里进行过滤
            if (supportsEvent(listener, event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    /**
     * 监听器是否对该事件感兴趣
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        // 可以理解这里拿到对应的 listener的泛型类的实际类型
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeanCreationException("wrong event class name: " + className);
        }
        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。
        // 如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        // // 就是前面的是否是后面的”爸爸“或者是其本身
        return eventClassName.isAssignableFrom(event.getClass());
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

    }

    @Override
    public void addApplicationListenerBean(String listenerBeanName) {

    }

    @Override
    public void removeApplicationListenerBean(String listenerBeanName) {

    }

    @Override
    public void removeAllListeners() {

    }

    @Override
    public void multicastEvent(ApplicationEvent event) {

    }
}
