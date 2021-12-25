package com.simple.simplespring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 08:38
 **/
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    /**
     * Declares whether the annotated dependency is required.
     * <p>Defaults to {@code true}.
     */
    boolean required() default true;
}
