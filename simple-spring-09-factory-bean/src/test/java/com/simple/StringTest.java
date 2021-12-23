package com.simple;

import com.simple.simplespring.util.StringUtils;
import org.junit.Test;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-22 08:23
 **/
public class StringTest {

    @Test
    public void testStrClean(){
        String path1 = "E:\\123\\..\\1.txt";
        // E:/1.txt
        System.out.println(StringUtils.cleanPath(path1));

        String path2 = "E:\\123\\1.txt\\.";
        // E:/123/1.txt
        System.out.println(StringUtils.cleanPath(path2));
    }

    @Test
    public void applyRelativePathTest(){
        String path1 = "/1/2/3.txt";
        String path2 = "4/5.txt";
        // /1/2/4/5
        System.out.println(StringUtils.applyRelativePath(path1, path2));
    }
}
