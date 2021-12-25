package com.simple.simplespring.util;

import com.sun.istack.internal.Nullable;

import java.util.Collection;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 00:27
 **/
public abstract class CollectionUtils {

    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }
}
