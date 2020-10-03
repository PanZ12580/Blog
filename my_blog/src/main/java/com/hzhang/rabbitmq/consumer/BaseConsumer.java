package com.hzhang.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/9/6 0:09
 * @description：邮件发送消费者
 * @modified By：
 * @version: $
 */
public interface BaseConsumer {
    /**
     * 消费
     * @param message
     * @param channel
     */
    boolean consumer(Message message, Channel channel);
}
