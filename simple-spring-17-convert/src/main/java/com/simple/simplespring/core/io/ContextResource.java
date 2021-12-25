package com.simple.simplespring.core.io;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 10:09
 **/
public interface ContextResource extends Resource {
    /**
     * 返回路径封闭的上下文
     *
     * @return
     */
    String getPathWithinContext();
}
