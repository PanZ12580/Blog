package com.hzhang.service;

import com.github.pagehelper.PageInfo;
import com.hzhang.annotation.ClearRedisCache;
import com.hzhang.pojo.Type;
import com.hzhang.service.impl.BlogServiceImpl;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/10 14:34
 * @description：博客后台分类管理相关
 * @modified By：
 * @version: $
 */
public interface TypeService {
    /**
     * 新增分类
     * @param type
     * @return 主键id
     */
    @ClearRedisCache
    Long saveType(Type type);

    /**
     * 根据id查询一个分类
     * @param id
     * @return Type
     */
    @Cacheable(cacheNames = "cache")
    Type findTypeById(Long id);

    /**
     * 根据名称查询分类
     * @param name
     * @return
     */
    @Cacheable(cacheNames = "cache")
    Type findTypeByName(String name);

    /**
     *查询分类列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Cacheable(cacheNames = "cache")
    PageInfo<Type> findTypeList(Integer currentPage, Integer pageSize);

    /**
     * 查询博客数目最多的前top条分类
     * @param top
     * @return
     */
    @Cacheable(cacheNames = "cache")
    List<Type> findTopTypeList(Integer top);

    /**
     * 修改分类
     * @param type
     * @return
     */
    @ClearRedisCache(cascade = BlogServiceImpl.class)
    int updateType(Type type);

    /**
     * 根据id删除分类
     * @param id
     */
    @ClearRedisCache(cascade = BlogServiceImpl.class)
    void deleteType(Long id);
}
