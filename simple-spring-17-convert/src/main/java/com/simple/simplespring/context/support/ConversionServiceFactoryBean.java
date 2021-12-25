package com.simple.simplespring.context.support;

import com.simple.simplespring.beans.factory.FactoryBean;
import com.simple.simplespring.beans.factory.InitializingBean;
import com.simple.simplespring.core.ConversionService;
import com.simple.simplespring.core.convert.converter.Converter;
import com.simple.simplespring.core.convert.converter.ConverterFactory;
import com.simple.simplespring.core.convert.converter.ConverterRegistry;
import com.simple.simplespring.core.convert.converter.GenericConverter;
import com.simple.simplespring.core.convert.support.DefaultConversionService;
import com.simple.simplespring.core.convert.support.GenericConversionService;
import com.sun.istack.internal.Nullable;

import java.util.Set;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 18:24
 **/
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {
    @Nullable
    private Set<?> converters;

    @Nullable
    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }
}
