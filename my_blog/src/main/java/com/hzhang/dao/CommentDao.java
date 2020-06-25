package com.hzhang.dao;

import com.hzhang.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/12 13:30
 * @description：评论实体DAO
 * @modified By：
 * @version: $
 */
@Repository
@Mapper
public interface CommentDao {
    /**
     * 根据博客id查询对应的评论数据
     * @param id
     * @return
     */
    List<Comment> findCommentListByBlogId(Long id);

    /**
     * 通过评论id查询该评论下的所有子评论
     * @param id
     * @return
     */
    List<Comment> findChildCommentsById(Long id);

    /**
     * 保存评论信息
     * @param comment
     * @return
     */
    int saveComment(Comment comment);
}
