package com.simple.simplespring.beans;

import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 18:45
 **/
public interface BeanMetadataElement {
    /**
     * 返回配置源信息
     *
     * @return
     */
    @Nullable
    default Object getSource() {
        return null;
    }
}
