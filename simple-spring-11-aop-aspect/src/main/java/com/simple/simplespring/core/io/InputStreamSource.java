package com.simple.simplespring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 功能描述: 这个接口是Spring中的顶级接口，里面就存在一个方法，获取输入流
 *
 * @author: WuChengXing
 * @create: 2021-12-21 23:44
 **/
public interface InputStreamSource {

    /**
     * 获取输入流，其直接继承者：
     * org.springframework.core.io.Resource
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
