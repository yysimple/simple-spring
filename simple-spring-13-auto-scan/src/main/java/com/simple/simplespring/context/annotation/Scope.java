package com.simple.simplespring.context.annotation;

import java.lang.annotation.*;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 22:31
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";
}
