package com.qiancijun.ctwing;

import com.qiancijun.ctwing.core.MQMessageReceiver;
import com.qiancijun.ctwing.core.SaveTask;
import com.qiancijun.ctwing.entity.Data;
import com.qiancijun.ctwing.service.LevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CtwingApplication {

    @Autowired
    private MQMessageReceiver receiver;

    @Autowired
    private static MQMessageReceiver r;

    @Autowired
    private SaveTask task;

    @Autowired
    private static SaveTask t;

    @PostConstruct
    public void init() {
        r = receiver;
        t = task;
    }

    public static void main(String[] args) {
        SpringApplication.run(CtwingApplication.class, args);
        r.receive();
        new Thread(t).start();
    }
}