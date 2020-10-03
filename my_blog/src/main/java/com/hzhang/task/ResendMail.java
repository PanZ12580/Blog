package com.hzhang.task;

import com.hzhang.config.RabbitConfig;
import com.hzhang.pojo.Mail;
import com.hzhang.service.MailService;
import com.hzhang.utils.MessageUtils;
import com.hzhang.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/9/28 20:32
 * @description：邮件重发任务
 * @modified By：
 * @version: $
 */
@Component
public class ResendMail {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MailService mailService;

    private static Logger logger = LoggerFactory.getLogger(ResendMail.class);

    private static final Integer MAX_COUNT = 3;

    @Scheduled(cron = "0/30 * * * * ?")
    @Async
    public void resend() {
        logger.info("开始启动定时任务");

        Mail mail = (Mail) redisUtil.lpop(RabbitConfig.MAIL_KEY);
        if (mail != null) {
            Integer retryCount = mail.getRetryCount();
            if (retryCount > MAX_COUNT) {
                logger.info("消息消费重试次数达到上限，放弃消费");
                return;
            }
            String msgId = mail.getMsgId();
            if (redisUtil.setIsMember(RabbitConfig.MAIL_KEY_SET, msgId)) {
                logger.info("消息 {} 重复消费", msgId);
                return;
            }

            logger.info("准备发送邮件：to：{}，subject：{}，content：{}", mail.getTo(), mail.getSubject(), mail.getContent());
            if (mailService.sendMail(mail.getTo(), mail.getSubject(), mail.getContent())) {
                logger.info("定时任务消息消费成功，消息id：{}", msgId);
                redisUtil.setAdd(RabbitConfig.MAIL_KEY_SET, msgId);
            } else {
                logger.info("邮件发送失败：to：{}，subject：{}，content：{}", mail.getTo(), mail.getSubject(), mail.getContent());
                mail.setRetryCount(mail.getRetryCount() + 1);
                mailService.setMail(mail);
            }
        }
    }
}
