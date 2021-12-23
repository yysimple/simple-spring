package com.simple;

import com.simple.event.ContextClosedEventListener;
import com.simple.event.CustomEvent;
import com.simple.event.SubContextClosedEventListener;
import com.simple.simplespring.context.ApplicationListener;
import com.simple.simplespring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 08:23
 **/
public class BeanTest {

    @Test
    public void testEvent() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }

    @Test
    public void testIsAssignableFrom(){
        ContextClosedEventListener contextClosedEventListener = new ContextClosedEventListener();
        SubContextClosedEventListener subContextClosedEventListener = new SubContextClosedEventListener();
        Class<? extends SubContextClosedEventListener> subContextClosedEventListenerClass = subContextClosedEventListener.getClass();
        Class<? extends ContextClosedEventListener> aClass = contextClosedEventListener.getClass();
        Class<ApplicationListener> applicationListenerClass = ApplicationListener.class;
        // 就是前面的是否是后面的”爸爸“或者是其本身
        if (aClass.isAssignableFrom(subContextClosedEventListenerClass)) {
            System.out.println(true);
        }
    }
}
