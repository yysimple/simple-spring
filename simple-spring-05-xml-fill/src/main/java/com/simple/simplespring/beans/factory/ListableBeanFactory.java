package com.simple.simplespring.beans.factory;

import com.simple.simplespring.beans.BeansException;

import java.util.Map;

/**
 * 功能描述: bean工厂的扩展类
 *
 * @author: WuChengXing
 * @create: 2021-12-22 10:50
 **/
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回所有的bean的定义信息
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
