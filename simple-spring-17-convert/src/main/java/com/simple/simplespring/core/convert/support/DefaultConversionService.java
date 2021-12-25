package com.simple.simplespring.core.convert.support;

import com.simple.simplespring.core.convert.converter.ConverterRegistry;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 18:09
 **/
public class DefaultConversionService extends GenericConversionService{
    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类类型转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
