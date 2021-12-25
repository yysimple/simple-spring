package com.simple.bean;

import com.simple.simplespring.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-25 09:21
 **/
@Component
public class UserMapper {
    private static final Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "simple，北京，亦庄");
        hashMap.put("10002", "wu，上海，尖沙咀");
        hashMap.put("10003", "zz，香港，铜锣湾");
    }

    public String queryUserName(String userId) {
        return hashMap.get(userId);
    }
}
