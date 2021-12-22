package com.simple.simplespring.beans.factory.config;

import com.simple.simplespring.beans.factory.HierarchicalBeanFactory;

/**
 * 功能描述: 这个类同样是对 bean工厂进行增强，再其基础之上增加了很多方法
 *
 * @author: WuChengXing
 * @create: 2021-12-22 10:52
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加自定义处理器
     *
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
