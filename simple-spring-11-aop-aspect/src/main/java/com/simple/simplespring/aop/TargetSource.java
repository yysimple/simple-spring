package com.simple.simplespring.aop;

/**
 * 功能描述: 该类在实际的AOP中是实现了 implements TargetClassAware
 * 用于通知的，这里先不做了
 *
 * @author: WuChengXing
 * @create: 2021-12-24 08:30
 **/
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
