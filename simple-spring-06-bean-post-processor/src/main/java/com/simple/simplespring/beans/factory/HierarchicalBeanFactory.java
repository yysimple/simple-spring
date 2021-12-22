package com.simple.simplespring.beans.factory;

import com.sun.istack.internal.Nullable;

/**
 * 功能描述: bean工厂的子接口，可以实现一种层次结构
 *
 * @author: WuChengXing
 * @create: 2021-12-22 16:42
 **/
public interface HierarchicalBeanFactory extends BeanFactory {
    /**
     * Return the parent bean factory, or {@code null} if there is none.
     */
    // @Nullable
    //BeanFactory getParentBeanFactory();

    /**
     * Return whether the local bean factory contains a bean of the given name,
     * ignoring beans defined in ancestor contexts.
     * <p>This is an alternative to {@code containsBean}, ignoring a bean
     * of the given name from an ancestor bean factory.
     * @param name the name of the bean to query
     * @return whether a bean with the given name is defined in the local factory
     * @see BeanFactory#containsBean
     */
    // boolean containsLocalBean(String name);
}
