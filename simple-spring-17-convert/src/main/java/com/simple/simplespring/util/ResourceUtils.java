package com.simple.simplespring.util;

import cn.hutool.core.util.StrUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 00:04
 **/
public class ResourceUtils {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";
    public static final String URL_PROTOCOL_FILE = "file";
    public static final String URL_PROTOCOL_VFSFILE = "vfsfile";
    public static final String URL_PROTOCOL_VFS = "vfs";

    public static URI toURI(URL url) throws URISyntaxException {
        return toURI(url.toString());
    }

    public static URI toURI(String location) throws URISyntaxException {
        return new URI(StrUtil.replace(location, " ", "%20"));
    }

    public static boolean isFileURL(URL url) {
        String protocol = url.getProtocol();
        return (URL_PROTOCOL_FILE.equals(protocol) || URL_PROTOCOL_VFSFILE.equals(protocol) ||
                URL_PROTOCOL_VFS.equals(protocol));
    }
}
