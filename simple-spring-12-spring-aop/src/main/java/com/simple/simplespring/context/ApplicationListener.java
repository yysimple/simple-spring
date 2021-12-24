package com.simple.simplespring.context;

import java.util.EventListener;

/**
 * 功能描述: 监听者
 *
 * @author: WuChengXing
 * @create: 2021-12-23 15:26
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
