package com.simple.simplespring.beans.factory;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-20 16:24
 **/
public interface BeanFactory {

    /**
     * Access to the bean
     *
     * @param name
     * @return
     */
    Object getBean(String name);


}
