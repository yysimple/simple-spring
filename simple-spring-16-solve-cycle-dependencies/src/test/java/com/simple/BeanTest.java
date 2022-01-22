package com.simple;

import com.simple.bean.Husband;
import com.simple.bean.Wife;
import com.simple.simplespring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 17:32
 **/
public class BeanTest {
    @Test
    public void test_circular() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        husband.setWife(wife);
        System.out.println();
        System.out.println("老公的媳妇：" + husband.queryWife());
//        System.out.println("媳妇的老公：" + wife.queryHusband());
    }
}
