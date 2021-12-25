package com.simple.simplespring.core;

import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 18:07
 **/
public interface ConversionService {

    /**
     * Return {@code true} if objects of {@code sourceType} can be converted to the {@code targetType}.
     * 判断是否能进行转换
     *
     * @param sourceType
     * @param targetType
     * @return
     */
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    /**
     * Convert the given {@code source} to the specified {@code targetType}.
     * 进行转换
     *
     * @param source
     * @param targetType
     * @param <T>
     * @return
     */
    <T> T convert(Object source, Class<T> targetType);
}
