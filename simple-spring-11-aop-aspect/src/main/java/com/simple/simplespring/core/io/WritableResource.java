package com.simple.simplespring.core.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 00:36
 **/
public interface WritableResource extends Resource {

    /**
     * 是否可写
     *
     * @return
     */
    default boolean isWritable() {
        return true;
    }

    /**
     * 获取文件的输出流
     *
     * @return
     * @throws IOException
     */
    OutputStream getOutputStream() throws IOException;

    /**
     * 将输出流也转成nio的channel来处理
     *
     * @return
     * @throws IOException
     */
    default WritableByteChannel writableChannel() throws IOException {
        return Channels.newChannel(getOutputStream());
    }
}
