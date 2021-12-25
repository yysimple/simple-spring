package com.simple.simplespring.beans.factory;

/**
 * 功能描述: 初始化过程中操作的bean
 *
 * @author: WuChengXing
 * @create: 2021-12-22 23:24
 **/
public interface InitializingBean {
    /**
     * 这里就一个方法，也就是在属性填充之后，开始的操作
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
