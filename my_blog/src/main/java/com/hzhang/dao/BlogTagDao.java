package com.hzhang.dao;

import com.hzhang.pojo.BlogTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/21 12:37
 * @description：博客标签实体中间类Dao层
 * @modified By：
 * @version: $
 */
@Mapper
@Repository
public interface BlogTagDao {
    /**
     * 通过标签Id查找博客Id列表
     * @param tagId
     * @return
     */
    List<BlogTag> findBlogTagByTagId(@Param("tagId") Long tagId);

    /**
     * 通过博客Id查找标签Id列表
     * @param blogId
     * @return
     */
    List<BlogTag> findBlogTagByBlogId(@Param("blogId") Long blogId);

    /**
     * 通过标签Id查找该标签对应的博客数量
     * @param tagId
     * @return
     */
    Integer findCountByTagId(@Param("tagId") Long tagId);
}
