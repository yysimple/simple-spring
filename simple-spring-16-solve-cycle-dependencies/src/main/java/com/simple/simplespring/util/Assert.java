package com.simple.simplespring.util;

import com.sun.istack.internal.Nullable;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 15:02
 **/
public abstract class Assert {

    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
