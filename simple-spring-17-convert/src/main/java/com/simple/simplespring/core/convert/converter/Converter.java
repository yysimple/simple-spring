package com.simple.simplespring.core.convert.converter;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 18:01
 **/
public interface Converter<S, T> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source
     * @return
     */
    T convert(S source);
}
