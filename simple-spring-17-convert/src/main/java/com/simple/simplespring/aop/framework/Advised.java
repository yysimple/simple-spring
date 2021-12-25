package com.simple.simplespring.aop.framework;

import com.simple.simplespring.aop.Advisor;
import com.simple.simplespring.aop.ClassFilter;
import com.simple.simplespring.aop.TargetClassAware;
import com.simple.simplespring.aop.TargetSource;
import org.aopalliance.aop.Advice;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-24 08:25
 **/
public interface Advised extends TargetClassAware {

    /**
     * 切面配置成 Frozne 模式
     *
     * @return
     */
    boolean isFrozen();

    /**
     * 目标是否是类，为不是接口
     */
    boolean isProxyTargetClass();

    /**
     * 返回aop代理的所有接口
     *
     * @return
     */
    Class<?>[] getProxiedInterfaces();

    /**
     * 代理的是否是接口
     *
     * @param intf
     * @return
     */
    boolean isInterfaceProxied(Class<?> intf);

    /**
     * Change the {@code TargetSource} used by this {@code Advised} object.
     * <p>Only works if the configuration isn't {@linkplain #isFrozen frozen}.
     *
     * @param targetSource new TargetSource to use
     */
    void setTargetSource(TargetSource targetSource);

    /**
     * Return the {@code TargetSource} used by this {@code Advised} object.
     */
    TargetSource getTargetSource();

    /**
     * 设置对外暴露的代理
     *
     * @param exposeProxy
     */
    void setExposeProxy(boolean exposeProxy);

    /**
     * Return whether the factory should expose the proxy as a {@link ThreadLocal}.
     * <p>It can be necessary to expose the proxy if an advised object needs
     * to invoke a method on itself with advice applied. Otherwise, if an
     * advised object invokes a method on {@code this}, no advice will be applied.
     * <p>Getting the proxy is analogous to an EJB calling {@code getEJBObject()}.
     */
    boolean isExposeProxy();

    /**
     * Set whether this proxy configuration is pre-filtered so that it only
     * contains applicable advisors (matching this proxy's target class).
     * <p>Default is "false". Set this to "true" if the advisors have been
     * pre-filtered already, meaning that the ClassFilter check can be skipped
     * when building the actual advisor chain for proxy invocations.
     *
     * @see ClassFilter
     */
    void setPreFiltered(boolean preFiltered);

    /**
     * Return whether this proxy configuration is pre-filtered so that it only
     * contains applicable advisors (matching this proxy's target class).
     */
    boolean isPreFiltered();

    /**
     * Return the advisors applying to this proxy.
     *
     * @return a list of Advisors applying to this proxy (never {@code null})
     */
    Advisor[] getAdvisors();

    /**
     * @param advisor
     * @throws AopConfigException
     */
    void addAdvisor(Advisor advisor) throws AopConfigException;

    /**
     * Add an Advisor at the specified position in the chain.
     *
     * @param advisor the advisor to add at the specified position in the chain
     * @param pos     position in chain (0 is head). Must be valid.
     * @throws AopConfigException in case of invalid advice
     */
    void addAdvisor(int pos, Advisor advisor) throws AopConfigException;

    /**
     * Remove the given advisor.
     *
     * @param advisor the advisor to remove
     * @return {@code true} if the advisor was removed; {@code false}
     * if the advisor was not found and hence could not be removed
     */
    boolean removeAdvisor(Advisor advisor);

    /**
     * Remove the advisor at the given index.
     *
     * @param index index of advisor to remove
     * @throws AopConfigException if the index is invalid
     */
    void removeAdvisor(int index) throws AopConfigException;

    /**
     * Return the index (from 0) of the given advisor,
     * or -1 if no such advisor applies to this proxy.
     * <p>The return value of this method can be used to index into the advisors array.
     *
     * @param advisor the advisor to search for
     * @return index from 0 of this advisor, or -1 if there's no such advisor
     */
    int indexOf(Advisor advisor);

    /**
     * Replace the given advisor.
     * and the replacement is not or implements different interfaces, the proxy will need
     * to be re-obtained or the old interfaces won't be supported and the new interface
     * won't be implemented.
     *
     * @param a the advisor to replace
     * @param b the advisor to replace it with
     * @return whether it was replaced. If the advisor wasn't found in the
     * list of advisors, this method returns {@code false} and does nothing.
     * @throws AopConfigException in case of invalid advice
     */
    boolean replaceAdvisor(Advisor a, Advisor b) throws AopConfigException;

    /**
     * Add the given AOP Alliance advice to the tail of the advice (interceptor) chain.
     * <p>This will be wrapped in a DefaultPointcutAdvisor with a pointcut that always
     * applies, and returned from the {@code getAdvisors()} method in this wrapped form.
     * <p>Note that the given advice will apply to all invocations on the proxy,
     * even to the {@code toString()} method! Use appropriate advice implementations
     * or specify appropriate pointcuts to apply to a narrower set of methods.
     *
     * @param advice advice to add to the tail of the chain
     * @throws AopConfigException in case of invalid advice
     * @see #addAdvice(int, Advice)
     */
    void addAdvice(Advice advice) throws AopConfigException;

    /**
     * @param pos
     * @param advice
     * @throws AopConfigException
     */
    void addAdvice(int pos, Advice advice) throws AopConfigException;

    /**
     * Remove the Advisor containing the given advice.
     *
     * @param advice the advice to remove
     * @return {@code true} of the advice was found and removed;
     * {@code false} if there was no such advice
     */
    boolean removeAdvice(Advice advice);

    /**
     * Return the index (from 0) of the given AOP Alliance Advice,
     * or -1 if no such advice is an advice for this proxy.
     * <p>The return value of this method can be used to index into
     * the advisors array.
     *
     * @param advice the AOP Alliance advice to search for
     * @return index from 0 of this advice, or -1 if there's no such advice
     */
    int indexOf(Advice advice);

    /**
     * As {@code toString()} will normally be delegated to the target,
     * this returns the equivalent for the AOP proxy.
     *
     * @return a string description of the proxy configuration
     */
    String toProxyConfigString();
}
