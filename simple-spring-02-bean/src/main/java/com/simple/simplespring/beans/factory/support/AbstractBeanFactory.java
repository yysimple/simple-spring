package com.simple.simplespring.beans.factory.support;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.BeanCreationException;
import com.simple.simplespring.beans.factory.BeanFactory;
import com.simple.simplespring.beans.factory.config.BeanDefinition;

/**
 * 功能描述: 这里类似一个模板，将注册bean和获取bean的方法定义再这里
 * 但是在Spring中是非常复杂的，这里久先简单的实现一下
 *
 * @author: WuChengXing
 * @create: 2021-12-21 10:39
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * getBean 的方法底层其实是调用了 doGetBean，为了模仿的更像一点，这里也这样写，只是Spring的抽象bean工厂中的实现很复杂
     * 我们这里就命名一样，但是只是简单的实现
     *
     * @param name
     * @return
     */
    @Override
    public Object getBean(String name) {
        return doGetBean(name);
    }

    /**
     * spring中的实现，就算从缓存中拿到了数据，也不是直接返回，还需要做一系列的考虑
     * 我们这里只是简单的实现
     *
     * @param name
     * @return
     */
    protected Object doGetBean(String name) {
        Object singletonBean = getSingleton(name);
        if (singletonBean != null) {
            return singletonBean;
        }
        // 这里就是交给子类去实现，如果缓存中存在的话就直接拿出来使用
        BeanDefinition beanDefinition = getBeanDefinition(name);
        // 然后拿到bean的定义信息交给子类去创建对应的bean
        return createBean(name, beanDefinition);
    }

    /**
     * 这里是去获取bean的定义信息
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    public abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建Bean的方法，这个方法和上面的方法在Spring对应的该类中也是有子类来实现，相当于一个模板
     *
     * @param beanName
     * @param beanDefinition
     * @return
     * @throws BeanCreationException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanCreationException;
}
