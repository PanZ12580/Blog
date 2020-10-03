package com.hzhang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzhang.pojo.Comment;
import com.hzhang.pojo.Mail;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/9/26 18:58
 * @description：
 * @modified By：
 * @version: $
 */
@SpringBootTest
public class JackSonTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageConverter converter;

    @Test
    public void testComment() throws JsonProcessingException {
        Comment parentComment = new Comment();
        Comment childComment = new Comment();
        Comment grandsonComment = new Comment();

        parentComment.setId(1L);
        parentComment.setContent("parent");
        childComment.setId(2L);
        childComment.setContent("child");
        grandsonComment.setId(3L);
        grandsonComment.setContent("grandson");

        childComment.setParentComment(parentComment);

        List<Comment> list = new ArrayList<>();
        List<Comment> list2 = new ArrayList<>();
        list.add(childComment);
        list2.add(grandsonComment);

        parentComment.setChildComments(list);
        childComment.setChildComments(list2);

        grandsonComment.setParentComment(childComment);

        String str1 = objectMapper.writeValueAsString(parentComment);
        String str2 = objectMapper.writeValueAsString(childComment);
        String str3 = objectMapper.writeValueAsString(grandsonComment);
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);

        Comment readValue = objectMapper.readValue(str1, Comment.class);
        System.out.println(readValue);
    }

    @Test
    public void testMessage() throws JsonProcessingException {
        String s = "{\"id\":null,\"content\":\"啊啊啊啊啊啊啊啊啊啊啊\",\"adminComment\":null,\"createTime\":1601301998970,\"parentCommentId\":149,\"blogId\":16,\"blog\":null,\"childComments\":null,\"parentComment\":null,\"user\":{\"id\":10,\"username\":null,\"password\":null,\"nickname\":\"Hzhang\",\"email\":\"344096911@qq.com\",\"avatar\":\"https://pm1.narvii.com/6070/149318209e4efe7f10ac43410f488f67c2687df4_00.jpg\",\"type\":0,\"createTime\":1593007291810,\"updateTime\":1593007291810,\"ip\":\"127.0.0.1\",\"qq\":null,\"wechat\":null,\"blogList\":null}}";
        Comment comment = objectMapper.readValue(s, Comment.class);

        Mail mail = new Mail();
        mail.setSubject("dsfadsfas");
        mail.setTo("344096911@qq.com");
        mail.setContent("mail");

        String uuid = UUID.randomUUID().toString();
        MessageProperties properties = new MessageProperties();
        properties.setMessageId(uuid);
        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        mail.setMsgId(uuid);

        Message message = converter.toMessage(comment, properties);
        Object m = converter.fromMessage(message);

        System.out.println(m);
    }
}
