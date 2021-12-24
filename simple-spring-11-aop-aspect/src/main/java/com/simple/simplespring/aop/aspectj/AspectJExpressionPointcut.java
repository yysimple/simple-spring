package com.simple.simplespring.aop.aspectj;

import com.simple.simplespring.aop.ClassFilter;
import com.simple.simplespring.aop.IntroductionAwareMethodMatcher;
import com.simple.simplespring.aop.MethodMatcher;
import com.simple.simplespring.aop.support.AbstractExpressionPointcut;
import com.simple.simplespring.beans.BeansException;
import com.simple.simplespring.beans.factory.BeanFactory;
import com.simple.simplespring.beans.factory.BeanFactoryAware;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 功能描述: 定义实际的匹配规则
 * 这里其实都是在使用 org.aspectj.weaver.tools 这个工具类里面的东西去实现规则匹配
 *
 * @author: WuChengXing
 * @create: 2021-12-23 23:44
 **/
public class AspectJExpressionPointcut extends AbstractExpressionPointcut
        implements ClassFilter, IntroductionAwareMethodMatcher, BeanFactoryAware {

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();

    static {
        // 这里是注册以表达式的方式去匹配对应的类
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    private final PointcutExpression pointcutExpression;

    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, boolean hasIntroductions) {
        return false;
    }
}
