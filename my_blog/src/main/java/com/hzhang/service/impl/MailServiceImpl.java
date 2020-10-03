package com.hzhang.service.impl;

import com.hzhang.config.RabbitConfig;
import com.hzhang.pojo.Mail;
import com.hzhang.service.MailService;
import com.hzhang.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/28 16:25
 * @description：邮件发送服务实现
 * @modified By：
 * @version: $
 */
@Component
public class MailServiceImpl implements MailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public boolean sendMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            logger.info("邮件发送成功！to: {}, from: {}, subject: {}", to, from, subject);
        } catch (MessagingException e) {
            logger.error("邮件发送失败！to: {}, from: {}, subject: {}, 失败信息: {}", to, from, subject, e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public String getMailContent() {
        return (String) redisUtil.lpop(RabbitConfig.MAIL_KEY);
    }

    @Override
    public void setMail(Mail mail) {
        redisUtil.rpush(RabbitConfig.MAIL_KEY, mail, -1);
    }
}
