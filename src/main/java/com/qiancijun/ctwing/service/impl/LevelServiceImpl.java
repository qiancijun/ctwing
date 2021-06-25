package com.qiancijun.ctwing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiancijun.ctwing.dao.LevelDao;
import com.qiancijun.ctwing.entity.WaterLevel;
import com.qiancijun.ctwing.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl extends ServiceImpl<LevelDao, WaterLevel> implements LevelService {

    @Autowired
    private LevelDao levelDao;

    @Override
    public List<WaterLevel> getAllData(String id) {
        return levelDao.selectAll(id);
    }

    @Override
    public List<String> getDevices() {
        return levelDao.selectDevice();
    }

}
