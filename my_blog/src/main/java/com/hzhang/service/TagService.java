package com.hzhang.service;

import com.github.pagehelper.PageInfo;
import com.hzhang.annotation.ClearRedisCache;
import com.hzhang.pojo.Tag;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/10 14:34
 * @description：博客后台标签管理相关
 * @modified By：
 * @version: $
 */
public interface TagService {
    /**
     * 新增分类
     * @param tag
     * @return 主键id
     */
    @ClearRedisCache
    Long saveTag(Tag tag);

    /**
     * 根据id查询一个分类
     * @param id
     * @return Tag
     */
    @Cacheable(cacheNames = "cache")
    Tag findTagById(Long id);

    /**
     * 根据名称查询分类
     * @param name
     * @return
     */
    @Cacheable(cacheNames = "cache")
    Tag findTagByName(String name);

    /**
     *查询标签列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Cacheable(cacheNames = "cache")
    PageInfo<Tag> findTagList(Integer currentPage, Integer pageSize);

    /**
     * 修改分类
     * @param tag
     * @return
     */
    @ClearRedisCache
    int updateTag(Tag tag);

    /**
     * 根据id删除分类
     * @param id
     */
    @ClearRedisCache
    void deleteTag(Long id);
}
