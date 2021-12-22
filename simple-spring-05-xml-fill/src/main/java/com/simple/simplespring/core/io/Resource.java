package com.simple.simplespring.core.io;

import com.sun.istack.internal.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * 功能描述: 这里就是对之前的接口进行一些增强，这里可以先把对应的方法加进来
 * 就多一些工具类来操作
 *
 * @author: WuChengXing
 * @create: 2021-12-21 23:46
 **/
public interface Resource extends InputStreamSource {
    /**
     * 判断文件是否存在
     *
     * @return
     */
    boolean exists();

    /**
     * 是否可读？
     *
     * @return
     */
    default boolean isReadable() {
        return exists();
    }

    /**
     * 是否可打开
     *
     * @return
     */
    default boolean isOpen() {
        return false;
    }

    /**
     * 是否是文件
     *
     * @return
     */
    default boolean isFile() {
        return false;
    }

    /**
     * 通过网络URL获取对应的资源
     *
     * @return
     * @throws IOException
     */
    URL getURL() throws IOException;

    /**
     * 通过网络URL获取对应的资源
     *
     * @return
     * @throws IOException
     */
    URI getURI() throws IOException;

    /**
     * 获取文件信息
     *
     * @return
     * @throws IOException
     */
    File getFile() throws IOException;

    /**
     * 将输入流以nio的channel作为读取，然后转成ReadableByteChannel
     *
     * @return
     * @throws IOException
     */
    default ReadableByteChannel readableChannel() throws IOException {
        return Channels.newChannel(getInputStream());
    }

    /**
     * 对应内容长度
     *
     * @return
     * @throws IOException
     */
    long contentLength() throws IOException;

    /**
     * 最后修改资源的时间戳
     *
     * @return
     * @throws IOException
     */
    long lastModified() throws IOException;

    /**
     * 创建一个资源相对于这个资源。
     *
     * @param relativePath
     * @return
     * @throws IOException
     */
    Resource createRelative(String relativePath) throws IOException;

    /**
     * 获取文件名称
     *
     * @return
     */
    @Nullable
    String getFilename();

    /**
     * 获取描述信息
     *
     * @return
     */
    String getDescription();
}
