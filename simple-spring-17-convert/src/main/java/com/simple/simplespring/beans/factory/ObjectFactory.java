package com.simple.simplespring.beans.factory;

import com.simple.simplespring.beans.BeansException;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 16:47
 **/
public interface ObjectFactory<T> {

    /**
     * 获取代理对象
     *
     * @return
     * @throws BeansException
     */
    T getObject() throws BeansException;
}
