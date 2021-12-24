package com.simple.simplespring.aop.framework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 功能描述: 这里同样实在模仿Spring的做法，我们这里就是直接拿到Cglib通过ASM方式取得的增强类
 * 然后回调就只有 DynamicAdvisedInterceptor 拦截，而在Spring中提供了很多不同类型的拦截器：
 * - StaticUnadvisedInterceptor
 * - StaticUnadvisedExposedInterceptor
 * - DynamicUnadvisedInterceptor
 * - DynamicUnadvisedExposedInterceptor
 * - EqualsInterceptor
 * - HashCodeInterceptor
 * - FixedChainStaticTargetInterceptor
 * <p>
 * 我们就简单的实现
 *
 * @author: WuChengXing
 * @create: 2021-12-24 10:20
 **/
public class CglibAopProxy implements AopProxy {

    private final AdvisedSupport advised;

    public CglibAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        return enhancer.create();
    }

    /**
     * 这里继承的 MethodInterceptor 是cglib通过 ASM获取代理之后，给出的回调需要的方法拦截器
     */
    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private final AdvisedSupport advised;

        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, objects, methodProxy);
            if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
                // 而这里的是 org.aopalliance.intercept的，之后我们自己的逻辑就可以从这里去实现
                return advised.getMethodInterceptor().invoke(methodInvocation);
            }
            return methodInvocation.proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }

    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}
