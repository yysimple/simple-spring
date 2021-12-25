package com.simple.simplespring.aop.framework.autoproxy;

import com.simple.simplespring.aop.Advisor;
import com.simple.simplespring.aop.ClassFilter;
import com.simple.simplespring.aop.Pointcut;
import com.simple.simplespring.aop.TargetSource;
import com.simple.simplespring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.simple.simplespring.aop.framework.AdvisedSupport;
import com.simple.simplespring.aop.framework.ProxyFactory;
import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.PropertyValues;
import com.simple.simplespring.beans.factory.BeanFactory;
import com.simple.simplespring.beans.factory.BeanFactoryAware;
import com.simple.simplespring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.simple.simplespring.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * 功能描述: 自动代理类，这里也是将我们的代理逻辑融入到Spring当中
 * 这也是在bean的初始化之前会来执行该方法，同样在spring中，他的继承关系链会比较长
 * 我们这里只是简单的实现，其实原理都是一样的
 *
 * @author: WuChengXing
 * @create: 2021-12-24 15:29
 **/
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    /**
     * 在初始化bean之前执行
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        // 这里判断就是，如果传进来的类是那些类的子类，那么这里就不做处理
        if (isInfrastructureClass(beanClass)) {
            return null;
        }
        // 拿到所有注册的 表达式类
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            // 进行过滤，看这个类是否在 我们的表达式 范围内，是的话继续往下执行
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) {
                continue;
            }

            // 构建一个能够支持完成切面所需要的组合类
            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = null;
            try {
                // 拿到对应类的实例
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 下面这这个操作其实就是我们之前单独实现的切面一样的处理方式，只是下面是通过代理工厂来选择具体的代理对象的
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            // 默认使用的是JDK代理（再次强调，在JDK8及以上版本跟Cglib的性能几乎一样）
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();

        }

        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return null;
    }
}
