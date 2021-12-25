package com.simple.simplespring.beans.factory.config;

import com.simple.simplespring.beans.factory.HierarchicalBeanFactory;
import com.simple.simplespring.core.ConversionService;
import com.simple.simplespring.util.StringValueResolver;
import com.sun.istack.internal.Nullable;

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

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    /**
     * 获取类加载器
     *
     * @return
     */
    @Nullable
    ClassLoader getBeanClassLoader();

    /**
     * Add a String resolver for embedded values such as annotation attributes.
     * @param valueResolver the String resolver to apply to embedded values
     * @since 3.0
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * Resolve the given embedded value, e.g. an annotation attribute.
     * @param value the value to resolve
     * @return the resolved value (may be the original value as-is)
     * @since 3.0
     */
    String resolveEmbeddedValue(String value);

    /**
     * Specify a Spring 3.0 ConversionService to use for converting
     * property values, as an alternative to JavaBeans PropertyEditors.
     * @since 3.0
     */
    void setConversionService(ConversionService conversionService);

    /**
     * Return the associated ConversionService, if any.
     * @since 3.0
     */
    @Nullable
    ConversionService getConversionService();
}
