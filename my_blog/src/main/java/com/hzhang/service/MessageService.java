package com.hzhang.service;

import com.github.pagehelper.PageInfo;
import com.hzhang.annotation.ClearRedisCache;
import com.hzhang.pojo.Comment;
import com.hzhang.pojo.Message;
import com.hzhang.service.impl.UserServiceImpl;
import org.springframework.cache.annotation.Cacheable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/26 11:46
 * @description：留言业务逻辑层
 * @modified By：
 * @version: $
 */
public interface MessageService {

    /**
     * 查询留言列表
     * @return
     */
    @Cacheable(cacheNames = "cache")
    List<Message> findMessageList();

    /**
     * 保存留言信息
     * @param message
     * @param request
     * @return
     */
    @ClearRedisCache
    int saveMessage(Message message, HttpServletRequest request);
}
