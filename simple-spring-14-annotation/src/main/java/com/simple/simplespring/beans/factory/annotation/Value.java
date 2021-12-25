package com.simple.simplespring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 08:41
 **/
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    /**
     * The actual value expression &mdash; for example, <code>#{systemProperties.myProp}</code>.
     */
    String value();
}
