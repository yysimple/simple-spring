package com.simple.simplespring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.PropertyValue;
import com.simple.simplespring.beans.PropertyValues;
import com.simple.simplespring.beans.factory.BeanCreationException;
import com.simple.simplespring.beans.factory.DisposableBean;
import com.simple.simplespring.beans.factory.InitializingBean;
import com.simple.simplespring.beans.factory.config.AutowireCapableBeanFactory;
import com.simple.simplespring.beans.factory.config.BeanDefinition;
import com.simple.simplespring.beans.factory.config.BeanPostProcessor;
import com.simple.simplespring.beans.factory.config.BeanReference;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 功能描述: 这个类从spring的继承关系来看，这里只是负责去实现创建bean的功能，没有去实现获取bean的定义信息
 *
 * @author: WuChengXing
 * @create: 2021-12-21 10:59
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

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
            // ... todo 其他处理方法
            // ... 其实这还有很多其他的操作，这里掠过，先赋值一下
            Object exposedObject = bean;
            try {
                // 给 Bean 填充属性
                populateBean(beanName, beanDefinition, bean);
                // 依旧是模仿Spring，其再填充完属性之后，这里会去触发我们自己实现的或者内置的处理器：exposedObject = initializeBean(beanName, exposedObject, mbd);
                exposedObject = initializeBean(beanName, exposedObject, beanDefinition);
            } catch (Exception e) {
                throw new BeanCreationException(beanName, "Initialization of bean failed");
            }

            // ....中间还有其他的判断 todo
            try {
                // 这里是在bean完成初始化之后，也即差不多可以开始用的时候，将该bean的销毁方法进行注册，以供后续可能需要使用的地方去使用：If Necessary
                registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
            } catch (Exception ex) {
                throw new BeanCreationException(beanName, "Invalid destruction signature", ex);
            }
            return exposedObject;
        } catch (Exception e) {
            throw new BeanCreationException("Creation of bean failed", e);
        }
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
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

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 2. 这里会去执行对象的初始化方法
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 3. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    /**
     * 这里也会去回调设置的初始化方法；
     * 初始化阶段的资源是可以通过一下两种方式去获取的：
     * - 实现接口
     * - 定义在xml中
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1. 实现接口 InitializingBean
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 2. 注解配置 init-method
        String initMethodName = beanDefinition.getInitMethodName();
        // 判断是为了避免二次执行初始化
        if (StrUtil.isNotEmpty(initMethodName) && !(bean instanceof InitializingBean)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new BeanCreationException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
