package com.simple.simplespring.core.io;

import com.simple.simplespring.util.Assert;
import com.simple.simplespring.util.ClassUtils;
import com.simple.simplespring.util.ResourceUtils;
import com.simple.simplespring.util.StringUtils;
import com.sun.istack.internal.Nullable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 功能描述: 为了完成这个DefaultResourceLoader类的核心方法getResource，
 * 我们需要在为其添加几个类，本来是可以不需要的，但是其设计的太好了，忍不住引用一下
 * 这样扩展性更好，可用性更好
 *
 * @author: WuChengXing
 * @create: 2021-12-22 09:47
 **/
public class DefaultResourceLoader implements ResourceLoader {

    @Nullable
    private ClassLoader classLoader;

    private final Set<ProtocolResolver> protocolResolvers = new LinkedHashSet<>(4);

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        // 这里是引入一个函数式接口，需要我们自己去实现，不用已经存在的实现类。
        for (ProtocolResolver protocolResolver : getProtocolResolvers()) {
            Resource resource = protocolResolver.resolve(location, this);
            if (resource != null) {
                return resource;
            }
        }
        // 如果只是使用 “/” 开始的路径，那么我们需要从其上下文中去拿对应的资源,可以看下下面的注释的结果
        if (location.startsWith("/")) {
            return getResourceByPath(location);
            // 如果这里是 classpath: 开头的，就是类路径下的资源，去那里面获取
        } else if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()), getClassLoader());
        } else {
            // 其他情况就是从网络中获取
            try {
                URL url = new URL(location);
                return (ResourceUtils.isFileURL(url) ? new FileUrlResource(url) : new UrlResource(url));
            } catch (MalformedURLException ex) {
                // 其他情况走相对路径，文件目录的方式去获取
                return new FileSystemResource(location);
            }
        }
    }


    public void addProtocolResolver(ProtocolResolver resolver) {
        Assert.notNull(resolver, "ProtocolResolver must not be null");
        this.protocolResolvers.add(resolver);
    }

    public Collection<ProtocolResolver> getProtocolResolvers() {
        return this.protocolResolvers;
    }

    public DefaultResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    @Override
    public ClassLoader getClassLoader() {
        return (this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader());
    }

    protected Resource getResourceByPath(String path) {
        return new ClassPathContextResource(path, getClassLoader());
    }

    /**
     * 这个内部类的作用主要是去获取该类路劲下（文件上下文）的其他的相对文件
     */
    protected static class ClassPathContextResource extends ClassPathResource implements ContextResource {

        public ClassPathContextResource(String path, @Nullable ClassLoader classLoader) {
            super(path, classLoader);
        }

        @Override
        public String getPathWithinContext() {
            return getPath();
        }

        @Override
        public Resource createRelative(String relativePath) {
            // 这里比如是 /1/2/3.txt, 然后relativePath为4/5.txt；那么最后的资源路径就是 /1/2/4/5
            String pathToUse = StringUtils.applyRelativePath(getPath(), relativePath);
            return new ClassPathContextResource(pathToUse, getClassLoader());
        }
    }
}
