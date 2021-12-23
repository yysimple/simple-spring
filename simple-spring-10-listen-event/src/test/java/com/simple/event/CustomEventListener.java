package com.simple.event;

import com.simple.simplespring.context.ApplicationListener;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-23 16:13
 **/
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }

}
