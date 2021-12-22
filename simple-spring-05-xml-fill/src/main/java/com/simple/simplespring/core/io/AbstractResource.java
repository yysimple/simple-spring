package com.simple.simplespring.core.io;

import com.simple.simplespring.core.NestedIOException;
import com.simple.simplespring.util.ResourceUtils;
import com.sun.istack.internal.Nullable;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

/**
 * 功能描述: 这个是Spring中对应的实现，只是实现了部分公用的逻辑
 * 其实这种写法很好的，我们在平时写代码也应该有这种思维，以最少的类和最少的方法实现找到一个平衡点
 * 然后去实现一个具有很多功能的接口，而对应的一些实现类为了不需要去全部实现这些方法，就需要类似
 * 这里的抽象实现一样，将一些公共的方法去实现，这在策略模式下经常使用
 * <p>
 * -----
 * <p>
 * 而这里的话，其实还有一些方法是没有去实现的，因为我们只是简单的实现一个spring的资源加载，所以可能只会目录形式是一样的
 * 并不会去实现其那些工具方法
 *
 * @author: WuChengXing
 * @create: 2021-12-21 23:53
 **/
public abstract class AbstractResource implements Resource {

    Logger logger = Logger.getLogger("AbstractResource");

    /**
     * 这里只是一个简单的文件处理，其是这里下面还有许多子实现的，之后只会挑选我们后面需要简单实现加载的几个类
     *
     * @return
     */
    @Override
    public boolean exists() {
        // Try file existence: can we find the file in the file system?
        if (isFile()) {
            try {
                return getFile().exists();
            } catch (IOException ex) {
                // 这里是判断文件是否存在，直接用jdk的日志了，不用spring的了
                logger.info("Could not retrieve File for existence check of " + getDescription());
            }
        }
        // Fall back to stream existence: can we open the stream?
        try {
            getInputStream().close();
            return true;
        } catch (Throwable ex) {
            logger.info("Could not retrieve InputStream for existence check of " + getDescription());
            return false;
        }
    }

    @Override
    public boolean isReadable() {
        return exists();
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public URL getURL() throws IOException {
        throw new FileNotFoundException(getDescription() + " cannot be resolved to URL");
    }

    @Override
    public URI getURI() throws IOException {
        URL url = getURL();
        try {
            return ResourceUtils.toURI(url);
        } catch (URISyntaxException ex) {
            // 会抛出一个无效URL的异常
            throw new NestedIOException("Invalid URI [" + url + "]", ex);
        }
    }

    @Override
    public File getFile() throws IOException {
        throw new FileNotFoundException(getDescription() + " cannot be resolved to absolute file path");
    }

    @Override
    public ReadableByteChannel readableChannel() throws IOException {
        return Channels.newChannel(getInputStream());
    }

    /**
     * 记录字节长度
     *
     * @return
     * @throws IOException
     */
    @Override
    public long contentLength() throws IOException {
        InputStream is = getInputStream();
        try {
            long size = 0;
            byte[] buf = new byte[256];
            int read;
            while ((read = is.read(buf)) != -1) {
                size += read;
            }
            return size;
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                logger.info("Could not close content-length InputStream for " + getDescription());
            }
        }
    }

    @Override
    public long lastModified() throws IOException {
        File fileToCheck = getFileForLastModifiedCheck();
        long lastModified = fileToCheck.lastModified();
        if (lastModified == 0L && !fileToCheck.exists()) {
            throw new FileNotFoundException(getDescription() +
                    " cannot be resolved in the file system for checking its last-modified timestamp");
        }
        return lastModified;
    }

    protected File getFileForLastModifiedCheck() throws IOException {
        return getFile();
    }

    @Override
    public Resource createRelative(String relativePath) throws IOException {
        throw new FileNotFoundException("Cannot create a relative resource for " + getDescription());
    }

    @Override
    @Nullable
    public String getFilename() {
        return null;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return (this == other || (other instanceof Resource &&
                ((Resource) other).getDescription().equals(getDescription())));
    }

    @Override
    public int hashCode() {
        return getDescription().hashCode();
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
