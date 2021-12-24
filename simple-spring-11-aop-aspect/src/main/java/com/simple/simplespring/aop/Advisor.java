package com.simple.simplespring.aop;

import com.simple.aopalliance.Advice;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 10:03
 **/
public interface Advisor {

    /**
     * Common placeholder for an empty {@code Advice} to be returned from
     * {@link #getAdvice()} if no proper advice has been configured (yet).
     * @since 5.0
     */
    Advice EMPTY_ADVICE = new Advice() {};


    /**
     *
     * @return
     */
    Advice getAdvice();

    /**
     * Return whether this advice is associated with a particular instance
     * (for example, creating a mixin) or shared with all instances of
     * the advised class obtained from the same Spring bean factory.
     * <p><b>Note that this method is not currently used by the framework.</b>
     * Typical Advisor implementations always return {@code true}.
     * Use singleton/prototype bean definitions or appropriate programmatic
     * proxy creation to ensure that Advisors have the correct lifecycle model.
     * @return whether this advice is associated with a particular target instance
     */
    boolean isPerInstance();
}
