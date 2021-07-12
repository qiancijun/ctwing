package com.qiancijun.ctwing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiancijun.ctwing.dao.UserDao;
import com.qiancijun.ctwing.entity.User;
import com.qiancijun.ctwing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cheryl 769303522@qq.com
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    UserDao userDao;

    public int insertOne(User user) {
        return userDao.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateById(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userDao.deleteById(id);
    }

    @Override
    public User login(String email, String password) {
        User user = userDao.selectOne(new QueryWrapper<User>().eq("email", email).and(o ->
            o.eq("password", password)));
        return user;
    }

    @Override
    public String getOne(Integer id) {
        return userDao.selectById(id).getUsername();
    }

    @Override
    public int count() {
        return userDao.selectCount(null);
    }

}
