package com.simple.event;

import com.simple.simplespring.context.ApplicationListener;
import com.simple.simplespring.context.event.ContextClosedEvent;

/**
 * 功能描述: 关闭事件的监听者，监听的事件类型是 ContextClosedEvent
 *
 * @author: WuChengXing
 * @create: 2021-12-23 16:14
 **/
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
