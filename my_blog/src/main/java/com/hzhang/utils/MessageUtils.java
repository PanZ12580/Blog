package com.hzhang.utils;

import com.hzhang.pojo.Comment;
import com.hzhang.pojo.Mail;
import com.hzhang.utils.factory.CommentMailFactoryImpl;
import com.hzhang.utils.factory.MessageMailFactoryImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/9/25 17:39
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class MessageUtils {
    @Autowired
    private MessageConverter messageConverter;

    @Autowired
    private CommentMailFactoryImpl commentMailFactory;

    @Autowired
    private MessageMailFactoryImpl messageMailFactory;

    /**
     * 根据消息获取对应的邮件POJO对象
     * @param message
     * @return
     */
    public Mail getMail(Message message) {
        String messageId = message.getMessageProperties().getMessageId();
        Object obj = messageConverter.fromMessage(message);
        Mail mail = new Mail();
        if (obj instanceof com.hzhang.pojo.Message) {
            mail = messageMailFactory.getMail((com.hzhang.pojo.Message) obj);
        } else if (obj instanceof Comment) {
            mail = commentMailFactory.getMail((Comment) obj);
        }
        mail.setMsgId(messageId);
        return mail;
    }
}
