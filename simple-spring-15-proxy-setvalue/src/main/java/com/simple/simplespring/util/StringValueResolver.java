package com.simple.simplespring.util;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 08:24
 **/
public interface StringValueResolver {

    /**
     * 获取string的值
     *
     * @param strVal
     * @return
     */
    String resolveStringValue(String strVal);
}
