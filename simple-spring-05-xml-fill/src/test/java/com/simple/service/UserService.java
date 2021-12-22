package com.simple.service;

import com.simple.mapper.UserMapper;

/**
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2021-12-21 17:25
 **/
public class UserService {

    private String userId;

    private UserMapper userMapper;

    public String queryUserInfo() {
        System.out.println("查询用户信息：" + userMapper.queryUserName(userId));
        return userMapper.queryUserName(userId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String uId) {
        this.userId = uId;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
