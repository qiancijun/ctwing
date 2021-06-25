package com.qiancijun.ctwing.core;

import com.alibaba.fastjson.JSON;
import com.ctiot.aep.mqmsgpush.sdk.IMsgConsumer;
import com.ctiot.aep.mqmsgpush.sdk.IMsgListener;
import com.ctiot.aep.mqmsgpush.sdk.MqMsgConsumer;
import com.qiancijun.ctwing.entity.Data;
import com.qiancijun.ctwing.entity.WaterLevel;
import com.qiancijun.ctwing.service.LevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Component
public class MQMessageReceiver {
    @Value("${ctwing.server}")
    private String server;

    @Value("${ctwing.tenantId}")
    private String tenantId;

    @Value("${ctwing.token}")
    private String token;

    @Value("${ctwing.certFilePath}")
    private String certFilePath;

    @Autowired
    private LevelService levelService;

    @Autowired
    public Data d;

    public void receive() {
        log.info(server);
        log.info(tenantId);
        log.info(token);
        IMsgListener msgListener = new IMsgListener() {

            @Override
            public void onMessage(String msg) {
                Map data = JSON.parseObject(msg, Map.class);
//                log.info(data.toString());
                long timestamp = (Long) data.get("timestamp");
                String serviceId = (String) data.get("serviceId");
                Map payload = (Map) data.get("payload"); // 需要进一步处理
                String deviceId = (String) data.get("deviceId");
                BigDecimal level2 = (BigDecimal) payload.get("level");
                float level = level2.floatValue();
//                log.info(String.valueOf(timestamp));
//                log.info(serviceId);
//                log.info(payload.toString());
//                log.info(deviceId);
//                log.info(level2.toString());
//                log.info(String.valueOf(level));
                d.latesetData.put(deviceId, level);
                WaterLevel waterLevel = new WaterLevel(timestamp, serviceId, level, deviceId);
                d.waterLevel = waterLevel;
            }
        };


        //创建消息接收类
        IMsgConsumer consumer = new MqMsgConsumer();
        try {
            consumer.init(server, tenantId, token, certFilePath, null, msgListener);
            consumer.start();

            //程序退出时，停止接收、销毁
            //consumer.stop();
            //consumer.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


