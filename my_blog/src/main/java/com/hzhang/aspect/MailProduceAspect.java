package com.hzhang.aspect;

import com.hzhang.config.RabbitConfig;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/9/17 22:18
 * @description：rabbitMQ生产者代理
 * @modified By：
 * @version: $
 */
@Aspect
@Component
public class MailProduceAspect {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageConverter messageConverter;

    @Pointcut("execution(* com.hzhang.service.CommentService.saveComment(..))||execution(* com.hzhang.service.MessageService.saveMessage(..))")
    public void sendMail() {
    }

    @After("sendMail()")
    public void doAfter(JoinPoint joinPoint) {
        Object arg = joinPoint.getArgs()[0];

        String uuid = UUID.randomUUID().toString();
        MessageProperties properties = new MessageProperties();
        properties.setMessageId(uuid);
        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        Message message = messageConverter.toMessage(arg, properties);

        rabbitTemplate.convertAndSend(
                RabbitConfig.MAIL_EXCHANGE_NAME,
                RabbitConfig.MAIL_ROUTING_KEY,
                message,
                correlationData(message, uuid)
        );
    }

    /**
     * 设置消息相关数据
     * @param message
     * @param uuid
     * @return
     */
    public CorrelationData correlationData(Message message, String uuid) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setReturnedMessage(message);
        correlationData.setId(uuid);
        return correlationData;
    }
}
