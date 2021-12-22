package com.simple.simplespring.context.support;

import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.ConfigurableListableBeanFactory;
import com.simple.simplespring.beans.factory.config.AutowireCapableBeanFactory;
import com.simple.simplespring.beans.factory.config.BeanFactoryPostProcessor;
import com.simple.simplespring.beans.factory.config.BeanPostProcessor;
import com.simple.simplespring.context.ApplicationContext;
import com.simple.simplespring.context.ConfigurableApplicationContext;
import com.simple.simplespring.core.io.DefaultResourceLoader;
import com.simple.simplespring.core.io.ProtocolResolver;
import com.simple.simplespring.core.io.Resource;

import java.util.Collection;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 14:31
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    /**
     * 这里才是核心内容，我们这里简单的实现，一般说的bean的生命周期都是拿这个函数来说的，我们可以看看他调用了哪些方法：
     * - prepareRefresh()：刷新预处理，和主流程关系不大，就是保存了容器的启动时间，启动标志等
     * |
     * - ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory()：和主流程关系也不大，最终获得了DefaultListableBeanFactory
     * |
     * - prepareBeanFactory(beanFactory)：还是一些准备工作，添加了两个后置处理器ApplicationContextAwareProcessor，ApplicationListenerDetector
     * 还设置了 忽略自动装配 和 允许自动装配 的接口,如果不存在某个bean的时候，spring就自动注册singleton bean，还设置了bean表达式解析器 等
     * |
     * - postProcessBeanFactory(beanFactory)：这是一个空方法，通过我们MyBeanFactoryPostProcessorTest implements BeanFactoryPostProcessor
     * 那么spring在扫描的时候就会加载这个方法，这个是可以去修改 bean对一个的定义信息，也即 BeanDefinition的内容
     * |
     * - invokeBeanFactoryPostProcessors(beanFactory)：执行自定义的BeanFactoryProcessor和内置的BeanFactoryProcessor
     * |
     * - registerBeanPostProcessors(beanFactory)：注册BeanPostProcessor，这里是内外的都会进行注册
     * |
     * - initMessageSource()：注册消息相关的
     * |
     * - initApplicationEventMulticaster()：上下文事件相关的处理
     * |
     * - onRefresh()：这也是一个空方法，可以理解为在上面方法执行之后，可以加入自己希望实现的逻辑
     * |
     * - registerListeners()：处理Spring监听相关的内容
     * |
     * - finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory)：这里是用来初始化一些懒加载和还有为初始化的一些类
     * |
     * - finishRefresh()：这里会去清除资源缓存啊、发布事件等等
     * |
     * ------- 出现异常时：
     * |
     * - destroyBeans()；销毁bean
     * |
     * - cancelRefresh()：取消此处操作，并设置上下文存活状态为 false
     * |
     * ------- finally代码块中：
     * |
     * - resetCommonCaches()：会去重置所有的缓存
     * |
     * 好了，上面就是Spring中的refresh,总共调用了 {== 15个方法 ==}，也可以说时bean生命周期的函数，大概就是这些过程，接下来我们自己简单的实现一下：
     * - 支持BeanDefinition初始化后的操作
     * - bean实例化前后的操作
     *
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();

        // 2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 5. 这里的话是去实例化所有的bean，这里在Spring中应该是 finishBeanFactoryInitialization
        // preInstantiateSingletons这个方法的调用是在上面那个方法中，所以改造一下
        // beanFactory.preInstantiateSingletons();
        finishBeanFactoryInitialization(beanFactory);

        // ...其他相关的这里暂时不实现
    }

    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
        // ...这里还有一些其他的操作，这里先不实现了
        // Instantiate all remaining (non-lazy-init) singletons.
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 刷新beanFactory，等于是去创建bean工厂，可以理解为，每次动态更新bean的定义信息都需要去触发一下，让Spring的上下文有感知
     * 当然这个需要有子类去实现，这样才能完成 自动装配
     *
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 这个就是去拿到最新的beanFactory
     *
     * @return
     */
    @Override
    public abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 我们这里只是简简单单实现了该方法，其实在spring中，因为 除了支持BeanFactoryPostProcessor该接口的直接实现，还支持很对其之类的一些实现，
     * 也就是说，这里不仅仅是这么简单处理，直接调用方法即可，还需要一些其他前置操作，但是最后达成的目的都是一样的，就是在BeanDefinition初始化
     * 之后完成回调通知，允许其修改类的定义；在spring中，还有：
     * - BeanDefinitionRegistryPostProcessor：来看看其在Spring中的处理
     * List<BeanDefinitionRegistryPostProcessor> registryProcessors = new ArrayList<>();
     * ....
     * registryProcessor.postProcessBeanDefinitionRegistry(registry);
     * registryProcessors.add(registryProcessor);
     * ....
     * invokeBeanFactoryPostProcessors(registryProcessors, beanFactory);
     * |
     * 其实后可看到，最后也是去调用方法，所以原理都是一样的
     *
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        //拿到所有的 BeanFactoryPostProcessor 类型，一般都是我们去实现该类，然后实现其方法，完成我们自己的逻辑
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            // 这里去循环调用该方法，并把 beanFactory 传给对应的实现类，其实这也是一种“设计模式”里面，处理器理念
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 这里只是简单的注册BeanPostProcessors，将其方法BeanFactory中
     *
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    /**
     * 下面这些是空实现，这里是上层接口为了模仿Spring，看看人家的设计引入的很多方法
     * 这里很大一部分都是空实现，为了防止子类污染
     *
     * @param location
     * @return
     */
    @Override
    public Resource getResource(String location) {
        return super.getResource(location);
    }

    @Override
    public void addProtocolResolver(ProtocolResolver resolver) {
        super.addProtocolResolver(resolver);
    }

    @Override
    public Collection<ProtocolResolver> getProtocolResolvers() {
        return super.getProtocolResolvers();
    }

    public AbstractApplicationContext() {
        super();
    }

    @Override
    public ClassLoader getClassLoader() {
        return super.getClassLoader();
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return super.getResourceByPath(path);
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getApplicationName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public long getStartupDate() {
        return 0;
    }

    @Override
    public ApplicationContext getParent() {
        return null;
    }

    @Override
    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException {
        return null;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public void setParent(ApplicationContext parent) {

    }

    @Override
    public void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor) {

    }

    @Override
    public void registerShutdownHook() {
        // 这里就是环境上下文生命周期钩子，当应用停止，会去销毁bean
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // 拿到对应的bean工厂，然后调用其销毁方法
        getBeanFactory().destroySingletons();
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
