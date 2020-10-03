package com.hzhang.aspect;

import com.hzhang.pojo.Comment;
import com.hzhang.pojo.Message;
import com.hzhang.service.MailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.Date;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/28 16:33
 * @description：评论成功后邮件提醒
 * @modified By：
 * @version: $
 */
/*@Aspect
@Component*/
public class SendMailAspect {
    @Value("${mail.fromMail.addr}")
    private String defaultTo;

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Pointcut("execution(* com.hzhang.service.CommentService.saveComment(..))||execution(* com.hzhang.service.MessageService.saveMessage(..))")
    public void sendMail() {

    }

    @AfterReturning("sendMail()")
    @Async
    public void doAfterReturning(JoinPoint joinPoint) {
        Object arg = joinPoint.getArgs()[0];
        Context context = new Context();
        String to = defaultTo;
        if (arg instanceof Comment) {
            Comment parentComment = ((Comment) arg).getParentComment();
            to = parentComment == null ? defaultTo : parentComment.getUser().getEmail();
            if(parentComment != null) {
                context.setVariable("parentComment", parentComment);
                Date parentCommentCreateTime = new Date(parentComment.getCreateTime());
                context.setVariable("parentCommentCreateTime", parentCommentCreateTime);
            }
            context.setVariable("comment", (Comment) arg);
            context.setVariable("isComment", true);
            Date commentCreateTime = new Date(((Comment) arg).getCreateTime());
            context.setVariable("commentCreateTime", commentCreateTime);
        }
        else if (arg instanceof Message) {
            Message parentMessage = ((Message) arg).getParentMessage();
            to = parentMessage == null ? defaultTo : parentMessage.getUser().getEmail();
            if(parentMessage != null) {
                context.setVariable("parentComment", parentMessage);
                Date parentCommentCreateTime = new Date(parentMessage.getCreateTime());
                context.setVariable("parentCommentCreateTime", parentCommentCreateTime);
            }
            Message message = (Message) arg;
            message.setCreateTime(System.currentTimeMillis());
            context.setVariable("comment", message);
            context.setVariable("isComment", false);
            Date commentCreateTime = new Date(((Message) arg).getCreateTime());
            context.setVariable("commentCreateTime", commentCreateTime);
        }
        String emailContent = templateEngine.process("emailTemplate", context);
        try {
            mailService.sendMail(to, "主题: 博客回复通知", emailContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
