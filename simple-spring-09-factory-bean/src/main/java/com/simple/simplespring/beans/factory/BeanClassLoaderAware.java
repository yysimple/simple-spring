package com.simple.simplespring.beans.factory;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 10:12
 **/
public interface BeanClassLoaderAware extends Aware {
    /**
     * 将上下文环境的累加器返回
     *
     * @param classLoader
     */
    void setBeanClassLoader(ClassLoader classLoader);
}
