package com.hzhang.utils.factory;

import com.hzhang.pojo.Comment;
import com.hzhang.pojo.CommonMsg;
import com.hzhang.pojo.Mail;
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
@Component("commentMailFactory")
public class CommentMailFactoryImpl implements MailFactory {
    @Value("${mail.fromMail.addr}")
    private String defaultTo;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public Mail getMail(CommonMsg commonMsg) {
        String to;
        Context context = new Context();
        Mail mail = new Mail();
        if (commonMsg instanceof Comment) {
            Comment parentComment = ((Comment) commonMsg).getParentComment();
            to = parentComment == null ? defaultTo : parentComment.getUser().getEmail();
            if(parentComment != null) {
                context.setVariable("parentComment", parentComment);
                Date parentCommentCreateTime = new Date(parentComment.getCreateTime());
                context.setVariable("parentCommentCreateTime", parentCommentCreateTime);
            }
            context.setVariable("comment", (Comment) commonMsg);
            context.setVariable("isComment", true);
            Date commentCreateTime = new Date(((Comment) commonMsg).getCreateTime());
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
