package com.simple.bean;

import com.simple.simplespring.beans.factory.annotation.Autowired;
import com.simple.simplespring.beans.factory.annotation.Value;
import com.simple.simplespring.stereotype.Component;

import java.util.Random;

/**
 *
 */
@Component
public class UserService implements IUserService {

    @Value("${token}")
    private String token;

    @Autowired
    private UserMapper userMapper;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userMapper.queryUserName("10001") + "，" + token;
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }

    @Override
    public String toString() {
        return "UserService#token = { " + token + " }";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserMapper getUserDao() {
        return userMapper;
    }

    public void setUserDao(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
