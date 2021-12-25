package com.simple.simplespring.context.event;

/**
 * 功能描述: 上下文关闭事件
 *
 * @author: WuChengXing
 * @create: 2021-12-23 15:09
 **/
public class ContextClosedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
