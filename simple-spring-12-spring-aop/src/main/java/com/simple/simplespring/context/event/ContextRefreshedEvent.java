package com.simple.simplespring.context.event;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 15:10
 **/
public class ContextRefreshedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
