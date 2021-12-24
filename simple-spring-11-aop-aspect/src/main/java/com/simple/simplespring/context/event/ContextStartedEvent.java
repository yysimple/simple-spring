package com.simple.simplespring.context.event;

import com.simple.simplespring.context.ApplicationContext;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 15:20
 **/
public class ContextStartedEvent extends ApplicationContextEvent{

    /**
     * Create a new ContextStartedEvent.
     * @param source the {@code ApplicationContext} that has been started
     * (must not be {@code null})
     */
    public ContextStartedEvent(ApplicationContext source) {
        super(source);
    }
}
