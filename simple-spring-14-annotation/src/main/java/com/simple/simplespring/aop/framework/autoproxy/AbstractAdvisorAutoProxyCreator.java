package com.simple.simplespring.aop.framework.autoproxy;

import com.simple.simplespring.beans.factory.BeanFactory;
import com.simple.simplespring.beans.factory.ConfigurableListableBeanFactory;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 21:38
 **/
public abstract class AbstractAdvisorAutoProxyCreator extends AbstractAutoProxyCreator {

    @Nullable
    private BeanFactoryAdvisorRetrievalHelper advisorRetrievalHelper;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
        if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
            throw new IllegalArgumentException(
                    "AdvisorAutoProxyCreator requires a ConfigurableListableBeanFactory: " + beanFactory);
        }
        initBeanFactory((ConfigurableListableBeanFactory) beanFactory);
    }

    protected void initBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        this.advisorRetrievalHelper = new BeanFactoryAdvisorRetrievalHelperAdapter(beanFactory);
    }

    private class BeanFactoryAdvisorRetrievalHelperAdapter extends BeanFactoryAdvisorRetrievalHelper {

        public BeanFactoryAdvisorRetrievalHelperAdapter(ConfigurableListableBeanFactory beanFactory) {
            super(beanFactory);
        }
    }
}
