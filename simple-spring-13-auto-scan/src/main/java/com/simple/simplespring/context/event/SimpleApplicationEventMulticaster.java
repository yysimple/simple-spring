package com.simple.simplespring.context.event;

import com.simple.simplespring.beans.factory.BeanFactory;
import com.simple.simplespring.context.ApplicationEvent;
import com.simple.simplespring.context.ApplicationListener;

/**
 * 功能描述: 这里就是向所有已经注册了 对应事件类型 的 监听者发送消息：
 * ContextClosedEventListener implements ApplicationListener<ContextClosedEvent>
 * 这样，当我们在bean的销毁方法里面去推送事件：publishEvent(new ContextClosedEvent(this));
 * 那么 multicastEvent 里面就能拿到所有监听该事件类型的 监听者，然后告诉他们 这个事件已经发生
 *
 * 这里简单理解就是
 *  1. 我们自己写了个监听者 {@link com.simple.event.ContextClosedEventListener} ，他是监听 ”关闭事件“，然后通过配置文件或者注解的方式转成BeanDefinition
 *  然后等待==某个动作，这里是refresh中的registerListeners==将自己注册到 {@link AbstractApplicationEventMulticaster}
 *  的 applicationListeners 集合中；好了，他就不要动了，等待通知；
 *
 *  2. 接下来bean的生命周期流程，这里就是直接说调用 {@link com.simple.simplespring.context.support.AbstractApplicationContext}
 *  的 refresh() 方法，跟事件监听相关的也就三个方法：
 *      - initApplicationEventMulticaster()：这里就是去初始=事件发布者=就是去发送消息的，也就是该类{@link SimpleApplicationEventMulticaster}
 *      - registerListeners()：这里就是去拿到所有的监听者，全部添加到 applicationListeners 中；（关于isAssignableFrom的作用，测试类中有）
 *      - finishRefresh()：这里就是完成bean的初始化操作了，那么此时此刻....是不是需要干点什么？？？往下讲！！！
 *
 *  3. 完成上面的步骤之后，指定事件类型的监听者 有了；；可以给这些监听者通知的发布者 有了；；那是不是就差 发布者在什么时刻发送？？给哪些监听者？？发送什么东西？？
 *      - 发布者在什么时刻发送？？：这就要看什么场景了，比如上面的完成bean的初始化，那么我就发一个 刷新？？怎么发呢？？？
 *        finishRefresh 里面 调用publishEvent(new ContextRefreshedEvent(this)) 然后里面在调用 applicationEventMulticaster.multicastEvent(event)
 *        是不是 multicastEvent 就到该类中来去发送事件了。这个时候就触发了 //发布者在什么时刻发送//
 *      - 发送什么东西？？：先说这个，我们发啥？？new ContextRefreshedEvent(this)；从这个可以看出，我们发布了一个 ”刷新事件“
 *      - 给哪些监听者？？：上面已经知道发送的是 ”刷新事件“，那么我们怎么知道，哪些 监听者是在监听刷新事件呢？？{@link AbstractApplicationEventMulticaster}
 *        这个类里面的 getApplicationListeners(event)的方法就有过滤的功能，就可以发送给那些已经注册的了”监听者“
 *
 * 所以在Spring中这种设计，就是把 事件相关类 来作为类似枚举的东西，然后发布的时候 处理者（监听者）拿到这个有 发布者发布的事件之后，就可以干自己的事情
 *
 * @author: WuChengXing
 * @create: 2021-12-23 15:53
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            // 这里向所有的监听者发送消息
            listener.onApplicationEvent(event);
        }
    }
}
