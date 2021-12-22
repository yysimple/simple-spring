package com.simple.mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 17:25
 **/
public class UserMapper {

    private static final Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "simple");
        hashMap.put("10002", "吴同学");
        hashMap.put("10003", "张同学");
    }

    public String queryUserName(String userId) {
        return hashMap.get(userId);
    }
}
