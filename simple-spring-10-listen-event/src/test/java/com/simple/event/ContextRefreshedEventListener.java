package com.simple.event;

import com.simple.simplespring.context.ApplicationListener;
import com.simple.simplespring.context.event.ContextRefreshedEvent;

/**
 * 功能描述: 刷新事件的监听者，事件类型： ContextRefreshedEvent
 *
 * @author: WuChengXing
 * @create: 2021-12-23 16:14
 **/
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
