package com.hzhang.dao;

import com.hzhang.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/10 14:34
 * @description：博客后台标签管理相关
 * @modified By：
 * @version: $
 */
@Repository
@Mapper
public interface TagDao {
    /**
     * 新增分类
     * @param tag
     * @return 主键id
     */
    Long saveTag(Tag tag);

    /**
     * 根据id查询一个分类
     * @param id
     * @return Tag
     */
    Tag findTagById(Long id);

    /**
     * 根据名称查询分类
     * @param name
     * @return
     */
    Tag findTagByName(String name);

    /**
     * 查询分类列表
     * @return
     */
    List<Tag> findTagList();

    /**
     * 根据id修改分类
     * @param tag
     * @return
     */
    int updateTag(Tag tag);

    /**
     * 根据id删除分类
     * @param id
     */
    void deleteTag(Long id);

    /**
     * 根据博客id查询标签列表
     * @param id
     * @return
     */
    List<Tag> findTagListByBlogId(@Param("id") Long id);
}
