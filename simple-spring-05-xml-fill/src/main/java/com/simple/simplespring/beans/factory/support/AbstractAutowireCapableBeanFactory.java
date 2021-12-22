package com.simple.simplespring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.simple.simplespring.beans.PropertyValue;
import com.simple.simplespring.beans.PropertyValues;
import com.simple.simplespring.beans.factory.BeanCreationException;
import com.simple.simplespring.beans.factory.config.BeanDefinition;
import com.simple.simplespring.beans.factory.config.BeanReference;
import com.sun.istack.internal.Nullable;

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
     * <p>
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
            bean = doCreateBean(beanName, beanDefinition, args);
        } catch (Exception e) {
            throw new BeanCreationException("Creation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 这里的话来进行初始化bean和填充bean
     * 同样，这里在spring中很复杂，我们只是简单的实现，把核心的功能实现；因为这里还有是否存在循环依赖等校验，这里先不做
     *
     * @param beanName
     * @param beanDefinition
     * @param args
     * @return
     * @throws BeanCreationException
     */
    protected Object doCreateBean(final String beanName, final BeanDefinition beanDefinition, final @Nullable Object[] args)
            throws BeanCreationException {
        Object bean = null;
        try {
            // 这里需要修改成我们修改实例化的方法
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 给 Bean 填充属性
            populateBean(beanName, beanDefinition, bean);
        } catch (Exception e) {
            throw new BeanCreationException("Creation of bean failed", e);
        }
        return bean;
    }


    /**
     * 其实这个方法才是Spring里面属性填充的核心方法，不过这里面依旧很复杂：
     * - 包括了是否使用了注解（通过名字、通过类型）-- AUTOWIRE_BY_NAME、AUTOWIRE_BY_TYPE
     * - 是否实现了BeanPostProcessor
     * - 是否需要 needsDepCheck
     * <p>
     * 所以这里可以用于 createBean和autowire，这里我们只简单实现 createBean（其实是doCreateBean）所以这里在改造一下
     *
     * @param beanName
     * @param beanDefinition
     * @param bean
     */
    protected void populateBean(String beanName, BeanDefinition beanDefinition, Object bean) {

        // ...上面有很多校验，其实就是 PropertyValues 这里的值可能会发生改变，也就是支持动态注入的意思，这里就先不是实现了
        // 这里为了模仿的像一点，我们也传入 4 个参数，因为最后一个参数是会改变的，我们这里不支持动态改变，只是为了模仿
        if (beanDefinition.hasPropertyValues()) {
            applyPropertyValues(beanName, bean, beanDefinition, beanDefinition.getPropertyValues());
        } else {
            return;
        }

    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition, PropertyValues pvs) {
        try {
            PropertyValue[] propertyValues = pvs.getPropertyValues();
            for (PropertyValue pv : propertyValues) {
                String name = pv.getName();
                Object value = pv.getValue();
                // 判断此处依赖的是否是对象
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    String propertyName = beanReference.getBeanName();
                    // 通过beanName去拿到对应的实例
                    value = getBean(propertyName);
                }
                // 将该类赋值
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeanCreationException("Error setting property values：" + beanName);
        }
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
