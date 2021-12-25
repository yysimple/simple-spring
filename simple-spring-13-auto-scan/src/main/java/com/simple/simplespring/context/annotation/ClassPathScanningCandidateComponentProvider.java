package com.simple.simplespring.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.simple.simplespring.beans.factory.config.BeanDefinition;
import com.simple.simplespring.beans.factory.support.GenericBeanDefinition;
import com.simple.simplespring.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 22:32
 **/
public class ClassPathScanningCandidateComponentProvider {
    /**
     * 找到所有的指定 basePackage 下的使用了 @Component 注解的类，加入到候选人集合中
     *
     * @param basePackage
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new GenericBeanDefinition(clazz));
        }
        return candidates;
    }
}
