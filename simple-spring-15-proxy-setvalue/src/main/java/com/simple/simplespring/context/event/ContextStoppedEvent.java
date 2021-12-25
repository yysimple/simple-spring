package com.simple.simplespring.context.event;

import com.simple.simplespring.context.ApplicationContext;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 15:20
 **/
public class ContextStoppedEvent extends ApplicationContextEvent {

    /**
     * Create a new ContextStoppedEvent.
     *
     * @param source the {@code ApplicationContext} that has been stopped
     *               (must not be {@code null})
     */
    public ContextStoppedEvent(ApplicationContext source) {
        super(source);
    }
}
