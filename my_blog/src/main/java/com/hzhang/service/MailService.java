package com.hzhang.service;

import com.hzhang.pojo.Mail;

import javax.mail.MessagingException;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/28 16:23
 * @description：邮件发送服务
 * @modified By：
 * @version: $
 */
public interface MailService {
    /**
     * 发送邮件给指定对象
     * @param to
     * @param subject
     * @param content
     */
    boolean sendMail(String to, String subject, String content);

    /**
     * 从redis中获取邮件内容
     * @return
     */
    String getMailContent();

    /**
     * 将邮件存入redis中
     * @param mail
     */
    void setMail(Mail mail);

}
