package com.simple.simplespring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 08:38
 **/
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";
}
