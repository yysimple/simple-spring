package com.simple.simplespring.core.convert.support;

import com.simple.simplespring.core.convert.converter.Converter;
import com.simple.simplespring.core.convert.converter.ConverterFactory;
import com.simple.simplespring.util.NumberUtils;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述: 在Spring中存在很多这种内置的类型转换器
 *
 * @author: WuChengXing
 * @create: 2021-12-25 18:09
 **/
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        @Nullable
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            // 这里其实就是将字符串转换成数字类星星
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }
}
