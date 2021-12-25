package com.simple.converter;

import com.simple.simplespring.beans.factory.FactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 20:56
 **/
public class ConvertersFactoryBean implements FactoryBean<Set<?>> {
    @Override
    public Set<?> getObject() throws Exception {
        HashSet<Object> converters = new HashSet<>();
        StringToLocalDateConverter stringToLocalDateConverter = new StringToLocalDateConverter("yyyy-MM-dd");
        converters.add(stringToLocalDateConverter);
        return converters;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
