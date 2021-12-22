package com.simple.simplespring.core.io;

import com.simple.simplespring.util.ResourceUtils;
import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 09:47
 **/
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:".
     */
    String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

    /**
     * 这里就是已经有获取资源的方式了，从统一入口进入拿到那些资源，以供其他地方使用
     * 而且这里算是对资源获取做了一层包装，我觉得这里可以理解成这里是一种“外观”模式，
     * 用户不需要自己手动去通过那些是实现类去获取不同类型的资源，只需要传入 “符合指定规约” 的路径就可以了
     * 具体的实现不需要用户知道，用就完事了！！！
     *
     * @param location
     * @return
     */
    Resource getResource(String location);

    /**
     * 这里还提供了获取类加载器的方法，所以在spring中这些api算是非常全面了
     * 这也是spring高扩展性的一种体现
     *
     * @return
     */
    @Nullable
    ClassLoader getClassLoader();
}
