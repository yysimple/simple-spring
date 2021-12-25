package com.simple.simplespring.context.support;

import com.simple.simplespring.beans.factory.support.DefaultListableBeanFactory;
import com.simple.simplespring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 16:11
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            /**
             * 这步操作完成之后， {@link BeanDefinitionRegistry} 已经会有BeanDefinition的信息了
             */
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 具体的获取配置路径交给子类去完成：当然，这里只去实现ClassPath那部分的内容
     * 在spring当中解析xml都是传递 {@link XmlBeanDefinitionReader} 去完成的,
     * 所以再spring中，会把这个路径对应的资源再转一层，我们这里就不处理了，简单来实现
     *
     * @return
     */
    protected abstract String[] getConfigLocations();
}
