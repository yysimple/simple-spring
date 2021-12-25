package com.simple.simplespring.context;

/**
 * 功能描述: 事件发布者
 *
 * @author: WuChengXing
 * @create: 2021-12-23 15:22
 **/
public interface ApplicationEventPublisher {

    /**
     * 通知这些事件的订阅者
     *
     * @param event
     */
    void publishEvent(ApplicationEvent event);

    /**
     * 支持通过任意对象进行发布
     *
     * @param event
     */
    void publishEvent(Object event);
}
