package com.gwy.service.impl;

import com.gwy.annotation.Log;
import com.gwy.bean.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService {
    @Override
    @Log(value = "根据用户id获取用户信息")
    public User getUserById(String id) {
        User user = new User();
        user.setId(id);
        user.setName("张三"+id);
        user.setTel("tel"+id);
        return user;
    }

    @Override
    public User getUserByName(String name) {
        User user = new User();
        user.setId("param"+name);
        user.setName("张三"+name);
        user.setTel("tel"+name);
        return user;
    }
}