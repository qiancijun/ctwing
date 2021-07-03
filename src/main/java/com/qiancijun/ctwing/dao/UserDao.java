package com.qiancijun.ctwing.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiancijun.ctwing.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
