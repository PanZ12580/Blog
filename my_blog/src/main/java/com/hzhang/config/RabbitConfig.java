package com.hzhang.config;

import com.hzhang.service.MailService;
import com.hzhang.utils.MessageUtils;
import com.hzhang.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/9/6 0:16
 * @description：RabbitMQ配置类
 * @modified By：
 * @version: $
 */
@Configuration
public class RabbitConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private MailService mailService;

    @Autowired
    private MessageUtils messageUtils;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String MAIL_KEY = "mail.key";

    public static final String MAIL_EXCHANGE_NAME = "mail.exchange";

    public static final String MAIL_QUEUE_NAME = "mail.queue";

    public static final String MAIL_ROUTING_KEY = "mail.routing";

    public static final String MAIL_KEY_SET = "mail.key.set";

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

//        消息是否发送到Exchange
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                logger.info("邮件消息成功发送到Exchange");
            } else {
                logger.info("邮件消息发送失败，{}，错误信息：{}", correlationData, cause);
                mailService.setMail(messageUtils.getMail(correlationData.getReturnedMessage()));
            }
        });

//        触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            logger.info("消息从Exchange路由到Queue失败，exchange: {}, message: {}, routingKey: {}, replyCode: {}, replyText: {}", exchange, message, routingKey, replyCode, replyText);
            mailService.setMail(messageUtils.getMail(message));
        });

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(MAIL_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue mailQueue() {
        return new Queue(MAIL_QUEUE_NAME, true);
    }

    @Bean
    public Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(directExchange()).with(MAIL_ROUTING_KEY);
    }

}
