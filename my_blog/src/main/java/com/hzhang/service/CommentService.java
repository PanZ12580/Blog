package com.hzhang.service;

import com.hzhang.annotation.ClearRedisCache;
import com.hzhang.pojo.Comment;
import com.hzhang.service.impl.UserServiceImpl;
import org.springframework.cache.annotation.Cacheable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/24 15:33
 * @description：博客评论业务逻辑层
 * @modified By：
 * @version: $
 */
public interface CommentService {
    /**
     * 根据博客id查询对应的评论数据
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "cache")
    List<Comment> findCommentListByBlogId(Long id);

    /**
     * 保存评论信息
     * @param comment
     * @param request
     * @return
     */
    @ClearRedisCache(cascade = UserServiceImpl.class)
    int saveComment(Comment comment, HttpServletRequest request);
}
