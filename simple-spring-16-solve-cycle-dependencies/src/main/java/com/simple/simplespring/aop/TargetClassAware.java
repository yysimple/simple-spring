package com.simple.simplespring.aop;

import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 08:24
 **/
public interface TargetClassAware {

    /**
     * 获取目标类类型
     * @return
     */
    @Nullable
    Class<?> getTargetClass();
}
