package com.simple.converter;

import com.simple.simplespring.core.convert.converter.Converter;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 20:57
 **/
public class StringToIntegerConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}
