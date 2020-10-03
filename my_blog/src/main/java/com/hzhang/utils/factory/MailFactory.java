package com.hzhang.utils.factory;

import com.hzhang.pojo.CommonMsg;
import com.hzhang.pojo.Mail;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/9/5 22:52
 * @description：Thymeleaf上下文工厂
 * @modified By：
 * @version: $
 */
public interface MailFactory {
    /**
     * 获取评论或留言的邮件模板
     * @param commonMsg
     * @return
     */
    Mail getMail(CommonMsg commonMsg);
}
