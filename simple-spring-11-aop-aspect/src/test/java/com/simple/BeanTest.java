package com.simple;

import com.simple.bean.IUserService;
import com.simple.bean.UserService;
import com.simple.bean.UserServiceInterceptor;
import com.simple.simplespring.aop.TargetSource;
import com.simple.simplespring.aop.aspectj.AspectJExpressionPointcut;
import com.simple.simplespring.aop.framework.AdvisedSupport;
import com.simple.simplespring.aop.framework.CglibAopProxy;
import com.simple.simplespring.aop.framework.JdkDynamicAopProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 17:17
 **/
public class BeanTest {

    @Test
    public void testBaseAop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.simple.bean.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        // 匹配对应类中是否存在该方法
        System.out.println(pointcut.matches(method, clazz));

        // true、true
    }

    @Test
    public void testDynamic() {
        // 目标对象
        IUserService userService = new UserService();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.simple.bean.IUserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new CglibAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("花花"));
    }

}
