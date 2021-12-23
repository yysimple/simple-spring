package com.simple.simplespring.beans.factory;

/**
 * 功能描述: 销毁bean的操作
 *
 * @author: WuChengXing
 * @create: 2021-12-22 23:26
 **/
public interface DisposableBean {

    /**
     * 销毁bean
     *
     * @throws Exception
     */
    void destroy() throws Exception;
}
