package com.simple.simplespring.beans.factory.annotation;

import com.simple.simplespring.beans.factory.config.AutowireCapableBeanFactory;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 08:43
 **/
public enum Autowire {
    /**
     * Constant that indicates no autowiring at all.
     */
    NO(AutowireCapableBeanFactory.AUTOWIRE_NO),

    /**
     * Constant that indicates autowiring bean properties by name.
     */
    BY_NAME(AutowireCapableBeanFactory.AUTOWIRE_BY_NAME),

    /**
     * Constant that indicates autowiring bean properties by type.
     */
    BY_TYPE(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);


    private final int value;


    Autowire(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    /**
     * Return whether this represents an actual autowiring value.
     *
     * @return whether actual autowiring was specified
     * (either BY_NAME or BY_TYPE)
     */
    public boolean isAutowire() {
        return (this == BY_NAME || this == BY_TYPE);
    }
}
