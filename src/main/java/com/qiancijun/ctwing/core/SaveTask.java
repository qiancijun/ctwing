package com.qiancijun.ctwing.core;

import com.qiancijun.ctwing.entity.Data;
import com.qiancijun.ctwing.entity.KuTangInfo;
import com.qiancijun.ctwing.service.KuTangInfoService;
import com.qiancijun.ctwing.service.LevelService;
import com.qiancijun.ctwing.service.MailService;
import com.qiancijun.ctwing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@ConditionalOnClass(MQMessageReceiver.class)
@Component
@Slf4j
public class SaveTask implements Runnable {

    @Autowired
    private Data d;

    @Autowired
    private LevelService levelService;

    @Autowired
    KuTangInfoService kuTangInfoService;

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @Override
    public void run() {
        while (true) {
            if (d.waterLevel == null) {
                log.info("没有数据");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }
            // TODO 阈值的处理
            KuTangInfo info = null;
            if (d.kuTangcache.containsKey(d.waterLevel.getDeviceId())) {
                info = d.kuTangcache.get(d.waterLevel.getDeviceId());
            } else {
                info = kuTangInfoService.getOneInfoByServiceId(d.waterLevel.getDeviceId());
                d.kuTangcache.put(d.waterLevel.getDeviceId(), info);
            }
            if (d.waterLevel.getLevel() > info.getThreshold() && !d.set.contains(d.waterLevel.getDeviceId())) {
                // 超过阈值，需要警告
                Integer userId = info.getUserId();
                String username = userService.getOne(userId);
                String email = userService.getEmail(userId);
                try {
                    boolean res = mailService.sendMailTo(username, email, info.getName());
                    if (res) {
                        d.set.add(d.waterLevel.getDeviceId());
                        d.q.add(d.waterLevel.getDeviceId());
                        log.info(info.getName() + "警告成功");
                    } else {
                        log.info(info.getName() + "警告失败");
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }


            levelService.save(d.waterLevel);
            log.info("插入成功");
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
