package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.factory.BeanCreationException;
import com.simple.simplespring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 功能描述: 这个类从spring的继承关系来看，这里只是负责去实现创建bean的功能，没有去实现获取bean的定义信息
 *
 * @author: WuChengXing
 * @create: 2021-12-21 10:59
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 新的创建bean的方法
     *
     * spring中这里的实现非常复杂，可以理解分成 拿到类加载器、然后进行属性填充、然后进行bean的实例化
     * 这里简单实现，根据bean定义，然后直接通过反射拿到构造器信息进行初始化，然后添加到缓存中去
     *
     * @param beanName
     * @param beanDefinition
     * @param args
     * @return
     * @throws BeanCreationException
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanCreationException {
        Object bean = null;
        try {
            // 这里需要修改成我们修改实例化的方法
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            throw new BeanCreationException("Creation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 实例化bean的操作
     *
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        // 找到对应的构造器
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        // 默认使用的是Cglib的实例化对象的实现
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 这里可以实现切换实例化对象的实现
     *
     * @param instantiationStrategy
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
