package com.simple.simplespring.beans.factory;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 10:11
 **/
public interface BeanNameAware {

    /**
     * 将对应的bean的名称返回
     *
     * @param name
     */
    void setBeanName(String name);
}
