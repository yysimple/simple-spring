package com.simple.simplespring.aop.framework;

import com.simple.aopalliance.Advice;
import com.simple.simplespring.aop.Advisor;
import com.simple.simplespring.aop.MethodMatcher;
import com.simple.simplespring.aop.TargetSource;
import com.simple.simplespring.aop.aspectj.AspectJExpressionPointcut;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 功能描述: 切面的实现
 * 主要是用于把代理、拦截、匹配的各项属性包装到一个类中，方便在 Proxy 实现类进行使用
 * 这里的思路：
 *  - 首先 {@link AdvisedSupport} 这个类用来做代理的组合，就是代理需要的”材料“，这里都有
 *  - 完成一个切面的话，我们需要考虑什么？？怎么代理？？我要代理哪个类？？我代理好了应该告诉谁？？
 *      - 怎么代理：目前就是两种，通过Cglib使用ASM技术去完成类的代理，还有一个就是通过JDK提供的动态代理去完成
 *      - 代理哪个类：这里就需要传递一个类告诉代理方，这里使用 {@link TargetSource} 来做类传递
 *      - 代理好了应该告诉谁：现在就是我知道要代理哪个类，由谁去代理，那么我总不能只代理，然后无所作为吧，这里就是
 *        代理之后关联一个handler，我们这里是 {@link com.simple.bean.UserServiceInterceptor}去实现 MethodInterceptor
 *        但是，我们知道，我们每次操作是针对于类中方法的调用来进行回调通知的？？所以我们还要确定一件事，什么事呢？就是要知道我们需要去代理
 *        哪些方法，也就是，当哪些方法被调用的时候，去通知处理？？这里就需要一个定位方式，可以由很多种 注解啊 包匹配啊等等
 *        我们这里用的是 {@link AspectJExpressionPointcut} 的 SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
 *        也就是表达式匹配方式：new AspectJExpressionPointcut("execution(* com.simple.bean.IUserService.*(..))")
 *        这里的意思就是当IUserService所有的方法调用的时，都走我们定义的处理器，去拿到其执行切面
 *
 * @author: WuChengXing
 * @create: 2021-12-24 10:11
 **/
public class AdvisedSupport extends ProxyConfig implements Advised {

    /**
     * ProxyConfig：判断使用哪个代理，默认是jdk的
     * 两者区别：
     *  - jdk：JDK中的动态代理是通过反射类Proxy以及InvocationHandler回调接口实现的，
     *    但是，JDK中所要进行动态代理的类必须要实现一个接口，也就是说只能对该类所实现接口中定义的方法进行代理，这在实际编程中具有一定的局限性，而且使用反射的效率也并不是很高。
     *
     *  - cglib：动态生成一个要代理类的子类，子类重写要代理的类的所有不是final的方法。
     *    在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。它比使用java反射的JDK动态代理要快（jdk8做了很大的优化，几乎差不多了）。
     *    使用字节码处理框架ASM，来转换字节码并生成新的类。不鼓励直接使用ASM，因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉。
     */
    private boolean proxyTargetClass = false;

    /**
     * 被代理的目标对象
     */
    private TargetSource targetSource;

    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配器(检查目标方法是否符合通知条件)
     */
    private MethodMatcher methodMatcher;

    @Override
    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    @Override
    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    @Override
    public TargetSource getTargetSource() {
        return targetSource;
    }

    @Override
    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    @Override
    public Class<?> getTargetClass() {
        return null;
    }

    @Override
    public Class<?>[] getProxiedInterfaces() {
        return new Class[0];
    }

    @Override
    public boolean isInterfaceProxied(Class<?> intf) {
        return false;
    }

    @Override
    public void setPreFiltered(boolean preFiltered) {

    }

    @Override
    public boolean isPreFiltered() {
        return false;
    }

    @Override
    public Advisor[] getAdvisors() {
        return new Advisor[0];
    }

    @Override
    public void addAdvisor(Advisor advisor) throws AopConfigException {

    }

    @Override
    public void addAdvisor(int pos, Advisor advisor) throws AopConfigException {

    }

    @Override
    public boolean removeAdvisor(Advisor advisor) {
        return false;
    }

    @Override
    public void removeAdvisor(int index) throws AopConfigException {

    }

    @Override
    public int indexOf(Advisor advisor) {
        return 0;
    }

    @Override
    public boolean replaceAdvisor(Advisor a, Advisor b) throws AopConfigException {
        return false;
    }

    @Override
    public void addAdvice(Advice advice) throws AopConfigException {

    }

    @Override
    public void addAdvice(int pos, Advice advice) throws AopConfigException {

    }

    @Override
    public boolean removeAdvice(Advice advice) {
        return false;
    }

    @Override
    public int indexOf(Advice advice) {
        return 0;
    }

    @Override
    public String toProxyConfigString() {
        return null;
    }
}
