package com.qiancijun.ctwing.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiancijun.ctwing.entity.Data;
import com.qiancijun.ctwing.entity.WaterLevel;
import com.qiancijun.ctwing.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CtwingController {

    @Autowired
    private LevelService levelService;

    @Autowired
    private Data data;

    @GetMapping("/getCurrentData/{deviceId}")
    public float getCurrentData(@PathVariable("deviceId") String id) {
        return data.latesetData.get(id);
    }

    @GetMapping("/getData/{deviceId}")
    public List<WaterLevel> getDataByDeviceId(@PathVariable("deviceId") String id) {
        return levelService.getAllData(id);
    }

    @GetMapping("/getDevices")
    public List<String> getDeviceCount() {
        return levelService.getDevices();
    }
}
