package com.qiancijun.ctwing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiancijun.ctwing.entity.WaterLevel;
import com.qiancijun.ctwing.service.KuTangInfoService;
import com.qiancijun.ctwing.service.LevelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@SpringBootTest
class CtwingApplicationTests {

    @Autowired
    LevelService levelService;

    @Autowired
    KuTangInfoService kuTangInfoService;

    @Autowired
    JavaMailSender mailSender;

    @Test
    void contextLoads() {
        System.out.println(levelService.getDevices());
    }

    @Test
    public void testGetAll() {
        System.out.println(kuTangInfoService.getDetails(1));
    }

    @Test
    public void testMailSend() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);
        helper.setSubject("您的库塘水位已经超过阈值");
        helper.setText("测试");
        helper.setTo("769303522@qq.com");
        helper.setFrom("769303522@qq.com");
        mailSender.send(message);
    }

}
