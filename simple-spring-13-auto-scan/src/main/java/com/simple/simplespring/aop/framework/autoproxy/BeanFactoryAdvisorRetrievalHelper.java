package com.simple.simplespring.aop.framework.autoproxy;

import com.simple.simplespring.beans.factory.ConfigurableListableBeanFactory;
import com.simple.simplespring.util.Assert;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 21:49
 **/
public class BeanFactoryAdvisorRetrievalHelper {

    private final ConfigurableListableBeanFactory beanFactory;

    public BeanFactoryAdvisorRetrievalHelper(ConfigurableListableBeanFactory beanFactory) {
        Assert.notNull(beanFactory, "ListableBeanFactory must not be null");
        this.beanFactory = beanFactory;
    }
}
