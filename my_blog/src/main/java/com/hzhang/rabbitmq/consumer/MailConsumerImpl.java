package com.hzhang.rabbitmq.consumer;

import com.hzhang.config.RabbitConfig;
import com.hzhang.pojo.Mail;
import com.hzhang.service.MailService;
import com.hzhang.utils.MessageUtils;
import com.hzhang.utils.RedisUtil;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/9/6 0:11
 * @description：邮件发送消费者
 * @modified By：
 * @version: $
 */
@Component
public class MailConsumerImpl implements BaseConsumer {
    @Autowired
    private MailService mailService;

    @Autowired
    private RedisUtil redisUtil;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageUtils messageUtils;

    /**
     * 消费者监听
     * @param message
     * @param channel
     * @return
     */
    @Override
    @RabbitListener(queues = {RabbitConfig.MAIL_QUEUE_NAME})
    @Async
    public boolean consumer(Message message, Channel channel) {
        /**
         * AOP不起作用，代理需要手动实现，消息在此处转换为邮件内容寄出
         */
        //        验证消费幂等性
        String msgId = message.getMessageProperties().getMessageId();
        if (message == null || redisUtil.setIsMember(RabbitConfig.MAIL_KEY_SET, msgId)) {
            logger.info("消息：{} 重复消费！", msgId);
            return false;
        }

//        消费
        Mail mail = messageUtils.getMail(message);
        boolean res = false;
        try {
            String to = mail.getTo();
            String subject = mail.getSubject();

            res = mailService.sendMail(to, subject, mail.getContent());
        } catch (Exception e) {
            logger.debug("邮件消息发送出现异常，信息：{}", e.getMessage());
        }

//        判断消费是否成功
        long tag = message.getMessageProperties().getDeliveryTag();
        if (res) {
            redisUtil.setAdd(RabbitConfig.MAIL_KEY_SET, msgId);
            try {
                channel.basicAck(tag, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mailService.setMail(mail);
            try {
                channel.basicNack(tag, false, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return res;
    }
}
