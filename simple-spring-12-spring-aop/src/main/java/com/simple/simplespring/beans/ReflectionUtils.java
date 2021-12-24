package com.simple.simplespring.beans;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 14:57
 **/
@SuppressWarnings("all")
public class ReflectionUtils {

    /**
     * 将构造器设置成可访问，这里是直接从Spring里面拿出来的 反射工具类
     *
     * @param ctor
     */
    public static void makeAccessible(Constructor<?> ctor) {
        if ((!Modifier.isPublic(ctor.getModifiers()) || !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible()) {
            ctor.setAccessible(true);
        }
    }
}
