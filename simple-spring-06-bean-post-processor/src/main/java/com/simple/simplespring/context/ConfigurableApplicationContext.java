package com.simple.simplespring.context;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.ConfigurableListableBeanFactory;
import com.simple.simplespring.beans.factory.config.BeanFactoryPostProcessor;
import com.simple.simplespring.core.io.ProtocolResolver;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述: 这个类就有点意思，如果于其父类相比 ApplicationContext
 * 这里多了很多设置选项，而父类里面大部分都是获取上下文相关的内容，但是我们这里
 * 只会重点的去实现refresh，这些是自动装配的关键的地方
 *
 * @author: WuChengXing
 * @create: 2021-12-22 14:32
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 变量分隔符
     */
    String CONFIG_LOCATION_DELIMITERS = ",; \t\n";

    /**
     * 转换服务应用名
     */
    String CONVERSION_SERVICE_BEAN_NAME = "conversionService";

    String LOAD_TIME_WEAVER_BEAN_NAME = "loadTimeWeaver";

    /**
     * 环境变量
     */
    String ENVIRONMENT_BEAN_NAME = "environment";

    /**
     * 系统配置
     */
    String SYSTEM_PROPERTIES_BEAN_NAME = "systemProperties";

    String SYSTEM_ENVIRONMENT_BEAN_NAME = "systemEnvironment";

    String SHUTDOWN_HOOK_THREAD_NAME = "SpringContextShutdownHook";


    /**
     * 设置上下文id
     *
     * @param id
     */
    void setId(String id);

    /**
     * 设置该上下文的父亲上下文
     *
     * @param parent
     */
    void setParent(@Nullable ApplicationContext parent);

    /**
     * 设置环境变量
     * @param environment
     */
    // void setEnvironment(ConfigurableEnvironment environment);

    /**
     * 获取环境变量
     * @return
     */
    //@Override
    //ConfigurableEnvironment getEnvironment();

    /**
     * 添加一个 初始化 BeanDefinition 后的可以操作该bean定以的切入类
     *
     * @param postProcessor
     */
    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);

    /**
     * 添加上下文的监听器
     *
     * @param listener
     */
    // void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 添加自己的解析资源方式，上节我们也进行了这个的判断
     *
     * @param resolver
     */
    void addProtocolResolver(ProtocolResolver resolver);

    /**
     * 刷新容器：这个方法是我们这里要实现的重点内容
     *
     * @throws BeansException
     * @throws IllegalStateException
     */
    void refresh() throws BeansException, IllegalStateException;

    /**
     * 注册销毁的钩子函数
     */
    void registerShutdownHook();

    /**
     * 关闭上下文
     */
    //@Override
    //void close();

    /**
     * 判断该上下文是否存活状态
     *
     * @return
     */
    boolean isActive();

    /**
     * 获取增加后的 beanFactory
     *
     * @return
     * @throws IllegalStateException
     */
    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;
}
