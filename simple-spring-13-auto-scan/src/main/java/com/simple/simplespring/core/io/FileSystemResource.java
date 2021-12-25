package com.simple.simplespring.core.io;

import java.io.*;

/**
 * 功能描述: 通过文件获取的类，在spring当中是去直接继承AbstractResource的，所以我们这里也去模仿
 * 且这里还会去实现
 *
 * @author: WuChengXing
 * @create: 2021-12-22 00:31
 **/
public class FileSystemResource extends AbstractResource implements WritableResource {

    private final File file;

    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    /**
     * 这里就简单的实现一下，直接返回一个文件输入流即可
     *
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(this.file);
    }
}
