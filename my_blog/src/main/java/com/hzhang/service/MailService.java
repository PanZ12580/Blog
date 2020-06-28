package com.hzhang.service;

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
    void sendMail(String to, String subject, String content);
}
