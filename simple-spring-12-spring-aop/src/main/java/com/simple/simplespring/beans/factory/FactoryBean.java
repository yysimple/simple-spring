package com.simple.simplespring.beans.factory;

import com.sun.istack.internal.Nullable;

/**
 * 功能描述: 用于集成第三方框架
 *
 * @author: WuChengXing
 * @create: 2021-12-23 11:51
 **/
public interface FactoryBean<T> {

    String OBJECT_TYPE_ATTRIBUTE = "factoryBeanObjectType";

    /**
     * 获取工厂对象
     *
     * @return
     * @throws Exception
     */
    @Nullable
    T getObject() throws Exception;

    /**
     * 获取对象类型
     *
     * @return
     */
    @Nullable
    Class<?> getObjectType();

    /**
     * 该工厂对象是否是单例
     *
     * @return
     */
    default boolean isSingleton() {
        return true;
    }
}
