package com.qiancijun.ctwing.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiancijun.ctwing.entity.WaterLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LevelDao extends BaseMapper<WaterLevel> {
    List<WaterLevel> selectAll(@Param("id") String id);
    List<String> selectDevice();
}
