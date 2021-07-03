package com.qiancijun.ctwing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiancijun.ctwing.entity.WaterLevel;
import com.qiancijun.ctwing.service.KuTangInfoService;
import com.qiancijun.ctwing.service.LevelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class CtwingApplicationTests {

    @Autowired
    LevelService levelService;

    @Autowired
    KuTangInfoService kuTangInfoService;

    @Test
    void contextLoads() {
        System.out.println(levelService.getDevices());
    }

    @Test
    public void testGetAll() {
        System.out.println(kuTangInfoService.getAllInfo());
    }

}
