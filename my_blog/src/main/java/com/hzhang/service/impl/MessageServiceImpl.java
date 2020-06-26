package com.hzhang.service.impl;

import com.hzhang.dao.MessageDao;
import com.hzhang.dao.UserDao;
import com.hzhang.pojo.Message;
import com.hzhang.pojo.User;
import com.hzhang.service.MessageService;
import com.hzhang.utils.IpAddressUtils;
import com.hzhang.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/26 11:48
 * @description：留言业务逻辑层实现
 * @modified By：
 * @version: $
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${comment.avatar}")
    private String avatar;
    
    @Override
    public List<Message> findMessageList() {
        List<Message> messageList = messageDao.findMessageList();
        messageList.forEach(x -> x.getUser());
        List<Message> newMessageList = dealMessageList(messageList);
        return newMessageList;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int saveMessage(Message message, HttpServletRequest request) {
        message.setCreateTime(System.currentTimeMillis());
//        获取客户端ip地址
        String ipAddress = IpAddressUtils.getIpAddress(request);
//        从请求头中获取token信息
        String token = request.getHeader("token");
        if(!StringUtils.isEmpty(token)){
            User user = (User) redisUtil.get(token);
            if(user != null){
//                token校验通过则为管理员评论
                message.getUser().setId(user.getId());
                message.setAdminMessage(true);
                return messageDao.saveMessage(message);
            }
        }
//        普通用户评论，根据ip地址获取用户信息
        User userByIp = userDao.findUserByIp(ipAddress);
//        判断用户是否存在
        if(userByIp != null){
            message.getUser().setId(userByIp.getId());
        }
        else{
            message.getUser().setAvatar(avatar);
            message.getUser().setIp(ipAddress);
            message.getUser().setType(0);
            message.getUser().setCreateTime(System.currentTimeMillis());
            message.getUser().setUpdateTime(System.currentTimeMillis());
        }
        userDao.saveUser(message.getUser());
        return messageDao.saveMessage(message);
    }

    /**
     * 找到所有没有父评论的留言
     *
     * @param messageList
     * @return
     */
    public List<Message> dealMessageList(List<Message> messageList) {
        List<Message> newMessageList = new ArrayList<>();
        for (Message message : messageList) {
            if (message.getParentMsgId() == null) {
                newMessageList.add(message);
                List<Message> childList = getChildMessage(message, messageList);
                message.setChildMessages(childList);
                message.setChildMessagesCount(childList.size());
            }
        }
        return newMessageList;
    }

    /**
     * 对顶级留言进行处理，广度优先搜索，逐个加入链表，最后按时间降序排序后返回
     *
     * @param message
     * @param messageList
     * @return
     */
    public List<Message> getChildMessage(Message message, List<Message> messageList) {
        ArrayDeque<Message> queue = new ArrayDeque<>();
        List<Message> childList = new ArrayList<>();
        queue.addLast(message);
        while (!queue.isEmpty()) {
            Message tmpParent = queue.removeFirst();
            for (Message c : messageList) {
                if (c.getParentMsgId() != null && c.getParentMsgId().equals(tmpParent.getId())) {
                    Message tmp = new Message();
                    tmp.setId(tmpParent.getId());
                    tmp.setUser(tmpParent.getUser());
                    c.setParentMessage(tmp);
                    childList.add(c);
                    queue.addLast(c);
                }
            }
        }
        Collections.sort(childList, (a, b) -> (int) (b.getCreateTime() - a.getCreateTime()));
        return childList;
    }
}
