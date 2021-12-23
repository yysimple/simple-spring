package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.core.io.DefaultResourceLoader;
import com.simple.simplespring.core.io.ResourceLoader;

/**
 * 功能描述: 这里的话就是避免子类实现的类污染问题，还有就是抽取公共的方法
 * 之后的子类实现只需要专注去做解析文件的操作了，不要关心这里使用什么资源加载器去加载之类的
 *
 * @author: WuChengXing
 * @create: 2021-12-22 10:40
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    /**
     * 使用类加载器的默认实现
     *
     * @param registry
     */
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
