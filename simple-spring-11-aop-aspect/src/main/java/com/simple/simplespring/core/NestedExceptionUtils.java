package com.simple.simplespring.core;

import com.sun.istack.internal.Nullable;

/**
 * 功能描述: spring用来处理异常的工具类
 *
 * @author: WuChengXing
 * @create: 2021-12-22 00:07
 **/
public abstract class NestedExceptionUtils {
    /**
     * Build a message for the given base message and root cause.
     *
     * @param message the base message
     * @param cause   the root cause
     * @return the full exception message
     */
    @Nullable
    public static String buildMessage(@Nullable String message, @Nullable Throwable cause) {
        if (cause == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(64);
        if (message != null) {
            sb.append(message).append("; ");
        }
        sb.append("nested exception is ").append(cause);
        return sb.toString();
    }

    /**
     * Retrieve the innermost cause of the given exception, if any.
     *
     * @param original the original exception to introspect
     * @return the innermost exception, or {@code null} if none
     * @since 4.3.9
     */
    @Nullable
    public static Throwable getRootCause(@Nullable Throwable original) {
        if (original == null) {
            return null;
        }
        Throwable rootCause = null;
        Throwable cause = original.getCause();
        while (cause != null && cause != rootCause) {
            rootCause = cause;
            cause = cause.getCause();
        }
        return rootCause;
    }

    /**
     * Retrieve the most specific cause of the given exception, that is,
     * either the innermost cause (root cause) or the exception itself.
     * <p>Differs from {@link #getRootCause} in that it falls back
     * to the original exception if there is no root cause.
     *
     * @param original the original exception to introspect
     * @return the most specific cause (never {@code null})
     * @since 4.3.9
     */
    public static Throwable getMostSpecificCause(Throwable original) {
        Throwable rootCause = getRootCause(original);
        return (rootCause != null ? rootCause : original);
    }
}
