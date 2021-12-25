package com.simple.simplespring.context;

import com.simple.simplespring.beans.factory.HierarchicalBeanFactory;
import com.simple.simplespring.beans.factory.ListableBeanFactory;
import com.simple.simplespring.beans.factory.config.AutowireCapableBeanFactory;
import com.simple.simplespring.core.io.ResourceLoader;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述: spring 的全局上下文，这个类在spring的使用中也是重中之重
 * 其实该类的直接父类很多，这里先简单处理，之后慢慢补充，可以先看看：
 * EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory,
 * MessageSource, ApplicationEventPublisher, ResourcePatternResolver
 * <p>
 * 我们这里只继承 ListableBeanFactory, 来简单实现
 *
 * @author: WuChengXing
 * @create: 2021-12-22 14:28
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {

    /**
     * Return the unique id of this application context.
     *
     * @return the unique id of the context, or {@code null} if none
     */
    @Nullable
    String getId();

    /**
     * Return a name for the deployed application that this context belongs to.
     *
     * @return a name for the deployed application, or the empty String by default
     */
    String getApplicationName();

    /**
     * Return a friendly name for this context.
     *
     * @return a display name for this context (never {@code null})
     */
    String getDisplayName();

    /**
     * Return the timestamp when this context was first loaded.
     *
     * @return the timestamp (ms) when this context was first loaded
     */
    long getStartupDate();

    /**
     * Return the parent context, or {@code null} if there is no parent
     * and this is the root of the context hierarchy.
     *
     * @return the parent context, or {@code null} if there is no parent
     */
    @Nullable
    ApplicationContext getParent();

    /**
     * 获取对应的自动装配的bean
     *
     * @return
     * @throws IllegalStateException
     */
    AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException;
}
