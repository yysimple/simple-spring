package com.simple.simplespring.context;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.Aware;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 10:45
 **/
public interface ApplicationContextAware extends Aware {

    /**
     * 传递上下文环境
     *
     * @param applicationContext
     * @throws BeansException
     */
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
