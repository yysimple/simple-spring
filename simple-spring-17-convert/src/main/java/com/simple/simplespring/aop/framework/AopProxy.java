package com.simple.simplespring.aop.framework;

import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 10:17
 **/
public interface AopProxy {

    /**
     * 获取代理类
     *
     * @return
     */
    Object getProxy();

    /**
     * 还有就是通过类加载器获取
     *
     * @param classLoader
     * @return
     */
    Object getProxy(@Nullable ClassLoader classLoader);
}
