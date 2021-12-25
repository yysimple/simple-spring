package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.BeanCreationException;
import com.simple.simplespring.beans.factory.FactoryBean;
import com.simple.simplespring.beans.factory.config.BeanDefinition;
import com.simple.simplespring.beans.factory.config.BeanPostProcessor;
import com.simple.simplespring.beans.factory.config.ConfigurableBeanFactory;
import com.simple.simplespring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.simple.simplespring.core.ConversionService;
import com.simple.simplespring.util.Assert;
import com.simple.simplespring.util.ClassUtils;
import com.simple.simplespring.util.StringValueResolver;
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
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    /**
     * BeanPostProcessors to apply in createBean.
     */
    private final List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();

    /**
     * String resolvers to apply e.g. to annotation attribute values.
     */
    private final List<StringValueResolver> embeddedValueResolvers = new CopyOnWriteArrayList<>();

    /**
     * Indicates whether any InstantiationAwareBeanPostProcessors have been registered.
     */
    private volatile boolean hasInstantiationAwareBeanPostProcessors;

    private ConversionService conversionService;

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
        return doGetBean(name, null, null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, null, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return doGetBean(name, requiredType, null);
    }

    /**
     * spring中的实现，就算从缓存中拿到了数据，也不是直接返回，还需要做一系列的考虑
     * 我们这里只是简单的实现
     *
     * @param name
     * @return
     */
    protected <T> T doGetBean(final String name, @Nullable final Class<T> requiredType, @Nullable final Object[] args) {
        // 先从单例bean中获取对象
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        // 如果不是工厂bean，那么直接返回当前对象
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        // 先从 工厂对象 的缓存中去尝试获取
        Object object = getCachedObjectForFactoryBean(beanName);

        // 缓存中不存在的时候，从 工厂bean 中获取
        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        Assert.notNull(beanPostProcessor, "BeanPostProcessor must not be null");
        // Remove from old position, if any
        this.beanPostProcessors.remove(beanPostProcessor);
        // 如果是这种类型处理器，存在的话就设置为 true
        if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
            this.hasInstantiationAwareBeanPostProcessors = true;
        }
        /*if (beanPostProcessor instanceof DestructionAwareBeanPostProcessor) {
            this.hasDestructionAwareBeanPostProcessors = true;
        }*/
        // Add to end of list
        this.beanPostProcessors.add(beanPostProcessor);
    }

    protected boolean hasInstantiationAwareBeanPostProcessors() {
        return this.hasInstantiationAwareBeanPostProcessors;
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

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
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

    @Override
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }
}
