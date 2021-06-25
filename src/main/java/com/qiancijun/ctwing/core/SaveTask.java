package com.qiancijun.ctwing.core;

import com.qiancijun.ctwing.entity.Data;
import com.qiancijun.ctwing.service.LevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SaveTask implements Runnable {

    @Autowired
    private Data d;

    @Autowired
    private LevelService levelService;

    @Override
    public void run() {
        while (true) {
            if (d.waterLevel == null) {
                log.warn("没有数据");
                try {
                    // 5秒后重新获取
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }
            levelService.save(d.waterLevel);
            log.info("插入成功");
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
