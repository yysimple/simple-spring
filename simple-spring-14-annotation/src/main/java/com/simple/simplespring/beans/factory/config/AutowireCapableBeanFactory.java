package com.simple.simplespring.beans.factory.config;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.BeanFactory;

/**
 * 功能描述: 这个类中大部分都是自动装配相关的内容，也有自动销毁bean等
 * 可以理解这里能为 BeanFactory 增加极大的动态性
 *
 * @author: WuChengXing
 * @create: 2021-12-22 10:51
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {

    int AUTOWIRE_NO = 0;

    int AUTOWIRE_BY_NAME = 1;

    int AUTOWIRE_BY_TYPE = 2;

    int AUTOWIRE_CONSTRUCTOR = 3;

    @Deprecated
    int AUTOWIRE_AUTODETECT = 4;

    String ORIGINAL_INSTANCE_SUFFIX = ".ORIGINAL";

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
