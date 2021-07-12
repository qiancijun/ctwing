package com.qiancijun.ctwing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiancijun.ctwing.entity.User;

public interface UserService extends IService<User> {
    int insertOne(User user);
    int updateUser(User user);
    int deleteUser(Integer id);
    User login(String email, String password);
    String getOne(Integer id);
    int count();
}
