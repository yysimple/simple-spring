package com.simple.simplespring.context.event;

import com.simple.simplespring.context.ApplicationContext;
import com.simple.simplespring.context.ApplicationEvent;

/**
 * 功能描述: 这里的ApplicationContextEvent等一系列Event可以理解为之前我们在设计模式的学习中
 * 用来给接收者指定不同订阅类型的消息的枚举，比如想接受MQ，我们就订阅MQ消息，需要ShortMessage那就订阅其
 * public enum EventType {
 *     MQ,
 *     MESSAGE
 * }
 *
 *         // 初始化需要通知的事件 MQ/Message
 *         listenerManager = new ListenerManager(EventType.MQ, EventType.MESSAGE);
 *         // 订阅
 *         listenerManager.subscribe(EventType.MQ, new MQEventHandler());
 *         listenerManager.subscribe(EventType.MESSAGE, new MessageEventHandler());
 *
 * 在Spring中指定了四种类型：
 *  - ContextClosedEvent
 *  - ContextRefreshedEvent
 *  - ContextStartedEvent
 *  - ContextStoppedEvent
 *
 *  我们这里也同样定义这四种类型
 *
 *
 * @author: WuChengXing
 * @create: 2021-12-23 15:08
 **/
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * 获取上下文环境
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
