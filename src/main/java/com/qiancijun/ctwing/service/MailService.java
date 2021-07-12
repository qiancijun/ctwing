package com.qiancijun.ctwing.service;

import javax.mail.MessagingException;

public interface MailService {
    boolean sendMailTo(String dst, String name, String kuTang) throws MessagingException;
}
