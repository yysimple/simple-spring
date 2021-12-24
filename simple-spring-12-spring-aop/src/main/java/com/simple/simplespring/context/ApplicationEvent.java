package com.simple.simplespring.context;

import java.util.EventObject;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 15:02
 **/
public abstract class ApplicationEvent extends EventObject {

    private final long timestamp;

    /**
     * 创建一个新的应用事件
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Return the system time in milliseconds when the event occurred.
     */
    public final long getTimestamp() {
        return this.timestamp;
    }
}
