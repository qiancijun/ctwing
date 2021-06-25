package com.qiancijun.ctwing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiancijun.ctwing.entity.WaterLevel;

import java.util.List;

public interface LevelService extends IService<WaterLevel> {
    List<WaterLevel> getAllData(String id);
    List<String> getDevices();
}
