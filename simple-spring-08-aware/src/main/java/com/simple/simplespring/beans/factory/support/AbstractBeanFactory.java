package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.BeanCreationException;
import com.simple.simplespring.beans.factory.config.BeanDefinition;
import com.simple.simplespring.beans.factory.config.BeanPostProcessor;
import com.simple.simplespring.beans.factory.config.ConfigurableBeanFactory;
import com.simple.simplespring.util.Assert;
import com.simple.simplespring.util.ClassUtils;
import com.sun.istack.internal.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 功能描述: 这里类似一个模板，将注册bean和获取bean的方法定义再这里
 * 但是在Spring中是非常复杂的，这里久先简单的实现一下
 *
 * @author: WuChengXing
 * @create: 2021-12-21 10:39
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /**
     * BeanPostProcessors to apply in createBean.
     */
    private final List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();

    @Nullable
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     * getBean 的方法底层其实是调用了 doGetBean，为了模仿的更像一点，这里也这样写，只是Spring的抽象bean工厂中的实现很复杂
     * 我们这里就命名一样，但是只是简单的实现
     *
     * @param name
     * @return
     */
    @Override
    public Object getBean(String name) {
        return doGetBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    /**
     * spring中的实现，就算从缓存中拿到了数据，也不是直接返回，还需要做一系列的考虑
     * 我们这里只是简单的实现
     *
     * @param name
     * @return
     */
    protected <T> T doGetBean(String name, Object... args) {
        Object singletonBean = getSingleton(name);
        if (singletonBean != null) {
            return (T) singletonBean;
        }
        // 这里就是交给子类去实现，如果缓存中存在的话就直接拿出来使用
        BeanDefinition beanDefinition = getBeanDefinition(name);
        // 然后拿到bean的定义信息交给子类去创建对应的bean
        return (T) createBean(name, beanDefinition, args);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        Assert.notNull(beanPostProcessor, "BeanPostProcessor must not be null");
        // Remove from old position, if any
        this.beanPostProcessors.remove(beanPostProcessor);
        // 下面这两个方法，是Spring中对内置的处理器的处理，这里就不实现了
        /*if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
            this.hasInstantiationAwareBeanPostProcessors = true;
        }
        if (beanPostProcessor instanceof DestructionAwareBeanPostProcessor) {
            this.hasDestructionAwareBeanPostProcessors = true;
        }*/
        // Add to end of list
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 这里会缓存所有添加的 BeanPostProcessor，从这里去获取
     *
     * @return
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    @Override
    @Nullable
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    /**
     * 这里是去获取bean的定义信息
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    public abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 这里是将对象实例化传给两个实例化策略去做的
     *
     * @param beanName
     * @param beanDefinition
     * @param args
     * @return
     * @throws BeanCreationException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanCreationException;
}
