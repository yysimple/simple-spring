package com.simple.simplespring.core.io;

import com.simple.simplespring.util.Assert;
import com.simple.simplespring.util.ClassUtils;
import com.simple.simplespring.util.StringUtils;
import com.sun.istack.internal.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 功能描述: 类路劲加载资源
 * 这里说明一下：其实我们简单实现只需要传入一个 ClassLoader 就行，就能实现拿到资源
 * 这里只是模仿Spring源码，多加了允许使用Class类去获取类加载器，相当于扩展了一些功能
 * 另外两个类 UrlResource和FileSystemResource不会去扩展，可以自己参考源码去实现
 * 对应的，父类和父父类里面的工具方法这里也不会去实现，我们知道有这个东西即可
 *
 * @author: WuChengXing
 * @create: 2021-12-22 00:13
 **/
public class ClassPathResource extends AbstractFileResolvingResource {

    private final String path;

    @Nullable
    private ClassLoader classLoader;

    @Nullable
    private Class<?> clazz;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, @Nullable ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        // 会清除路径上一些没有用的东西
        String pathToUse = StringUtils.cleanPath(path);
        if (pathToUse.startsWith("/")) {
            pathToUse = pathToUse.substring(1);
        }
        this.path = pathToUse;
        // 这里是去获取类加载器
        this.classLoader = (classLoader != null ? classLoader : (this.getClassLoader() != null ? this.getClassLoader() : ClassUtils.getDefaultClassLoader()));
    }

    public ClassPathResource(String path, @Nullable Class<?> clazz) {
        Assert.notNull(path, "Path must not be null");
        this.path = StringUtils.cleanPath(path);
        this.clazz = clazz;
    }

    public final String getPath() {
        return this.path;
    }

    @Nullable
    public final ClassLoader getClassLoader() {
        return (this.clazz != null ? this.clazz.getClassLoader() : this.classLoader);
    }

    /**
     * 这里通过不同的方式去获取对应的 输入流
     *
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is;
        if (this.clazz != null) {
            is = this.clazz.getResourceAsStream(this.path);
        } else if (this.classLoader != null) {
            is = this.classLoader.getResourceAsStream(this.path);
        } else {
            is = ClassLoader.getSystemResourceAsStream(this.path);
        }
        if (is == null) {
            throw new FileNotFoundException(getDescription() + " cannot be opened because it does not exist");
        }
        return is;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
