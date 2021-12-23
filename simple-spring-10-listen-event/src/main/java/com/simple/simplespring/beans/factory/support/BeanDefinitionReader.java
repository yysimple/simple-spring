package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.core.io.Resource;
import com.simple.simplespring.core.io.ResourceLoader;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 10:34
 **/
public interface BeanDefinitionReader {

    /**
     * 获取bean的定义信息
     *
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     *
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 通过资源获取类的定义信息
     *
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;


    /**
     * 通过资源获取类的定义信息
     *
     * @param resources
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;


    /**
     * 通过资源获取类的定义信息
     *
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 通过多路径去加载资源，并获取指定的 BeanDefinition
     *
     * @param locations
     * @throws BeansException
     */
    void loadBeanDefinitions(String... locations) throws BeansException;

}
