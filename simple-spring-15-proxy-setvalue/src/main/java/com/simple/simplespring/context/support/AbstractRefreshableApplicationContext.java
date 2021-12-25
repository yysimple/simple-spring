package com.simple.simplespring.context.support;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.ConfigurableListableBeanFactory;
import com.simple.simplespring.beans.factory.support.DefaultListableBeanFactory;
import com.simple.simplespring.context.ApplicationContextException;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 15:55
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    /**
     * Synchronization monitor for the internal BeanFactory.
     */
    private final Object beanFactoryMonitor = new Object();

    private DefaultListableBeanFactory beanFactory;

    /**
     * 实现父类的刷新bean工厂，这里默认去创建一个 DefaultListableBeanFactory
     * 在Spring中，刷新bean工厂之前，还会先去销毁之前存在的bean，缓存之类的，这里就先不实现，可以上个todo
     *
     * @throws BeansException
     */
    @Override
    protected void refreshBeanFactory() throws BeansException {
        if (hasBeanFactory()) {
            // destroyBeans(); todo
            // closeBeanFactory(); todo
        }
        try {
            DefaultListableBeanFactory beanFactory = createBeanFactory();
            // beanFactory.setSerializationId(getId()); todo
            // customizeBeanFactory(beanFactory); todo 这里本来可以定制化操作，处理循环依赖等，这里先也不实现了
            loadBeanDefinitions(beanFactory);
            synchronized (this.beanFactoryMonitor) {
                this.beanFactory = beanFactory;
            }
        } catch (Exception ex) {
            throw new ApplicationContextException("I/O error parsing bean definition source for " + getDisplayName(), ex);
        }
    }

    protected final boolean hasBeanFactory() {
        synchronized (this.beanFactoryMonitor) {
            // return (this.beanFactory != null); todo
            return false;
        }
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 这里是获取资源并解析成 BeanDefinition的入口，由子类去实现
     *
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
