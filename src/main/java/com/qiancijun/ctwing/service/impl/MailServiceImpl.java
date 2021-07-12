package com.qiancijun.ctwing.service.impl;

import com.qiancijun.ctwing.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    @Override
    public boolean sendMailTo(String dst, String name, String kuTang) {
        try {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(d);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setSubject("您的库塘水位已经超过阈值");
            helper.setText("尊敬的" + name + "用户，您的" + kuTang + "水位占比已经超过阈值，请及时处理。\n" + format);
            helper.setTo(dst);
            helper.setFrom("769303522@qq.com");
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
