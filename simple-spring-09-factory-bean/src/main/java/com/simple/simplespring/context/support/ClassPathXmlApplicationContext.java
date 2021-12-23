package com.simple.simplespring.context.support;

import com.simple.simplespring.beans.BeansException;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 16:24
 **/
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     *
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     *
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        // 这里去触发刷新动作
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
