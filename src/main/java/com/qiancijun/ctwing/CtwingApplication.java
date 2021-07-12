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

    @Autowired
    private Data data;

    @Autowired
    private static Data d;

    @PostConstruct
    public void init() {
        r = receiver;
        t = task;
        d = data;
    }

    public static void main(String[] args) {
        SpringApplication.run(CtwingApplication.class, args);
        r.receive();
        if (t != null) new Thread(t).start();
        new Thread(() -> {
            if (!d.q.isEmpty()) {
                String poll = d.q.poll();
                d.set.remove(poll);
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}