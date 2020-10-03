package com.hzhang.utils.factory;

import com.hzhang.pojo.CommonMsg;
import com.hzhang.pojo.Mail;
import com.hzhang.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/9/5 22:53
 * @description：
 * @modified By：
 * @version: $
 */
@Component("messageMailFactory")
public class MessageMailFactoryImpl implements MailFactory {
    @Value("${mail.fromMail.addr}")
    private String defaultTo;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public Mail getMail(CommonMsg commonMsg) {
        String to;
        Context context = new Context();
        Mail mail = new Mail();
        if (commonMsg instanceof Message) {
            Message parentMessage = ((Message) commonMsg).getParentMessage();
            to = parentMessage == null ? defaultTo : parentMessage.getUser().getEmail();
            context.setVariable("to", to);
            if(parentMessage != null) {
                context.setVariable("parentComment", parentMessage);
                Date parentCommentCreateTime = new Date(parentMessage.getCreateTime());
                context.setVariable("parentCommentCreateTime", parentCommentCreateTime);
            }
            Message message = (Message) commonMsg;
            message.setCreateTime(System.currentTimeMillis());
            context.setVariable("comment", message);
            context.setVariable("isComment", false);
            Date commentCreateTime = new Date(((Message) commonMsg).getCreateTime());
            context.setVariable("commentCreateTime", commentCreateTime);
            String emailContent = templateEngine.process("emailTemplate", context);

            mail.setContent(emailContent);
            mail.setTo(to);
            mail.setSubject("主题: 博客回复通知");

            return mail;
        } else {
            return null;
        }
    }
}
