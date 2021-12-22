package com.simple.simplespring.core.io;

import com.sun.istack.internal.Nullable;

/**
 * 功能描述: 自己提供的实现，从这里去获取Resource
 *
 * @author: WuChengXing
 * @create: 2021-12-22 10:02
 **/
@FunctionalInterface
public interface ProtocolResolver {
    /**
     * 允许自己实现获取资源的方式
     *
     * @param location
     * @param resourceLoader
     * @return
     */
    @Nullable
    Resource resolve(String location, ResourceLoader resourceLoader);
}
