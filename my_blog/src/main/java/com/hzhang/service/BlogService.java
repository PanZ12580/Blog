package com.hzhang.service;

import com.github.pagehelper.PageInfo;
import com.hzhang.annotation.ClearRedisCache;
import com.hzhang.pojo.Archives;
import com.hzhang.pojo.Blog;
import com.hzhang.pojo.Type;
import com.hzhang.pojo.queryvo.BlogManageQueryVo;
import com.hzhang.service.impl.TagServiceImpl;
import com.hzhang.service.impl.TypeServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/12 10:38
 * @description：后台博客列表管理
 * @modified By：
 * @version: $
 */
public interface BlogService {
    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "cache")
    Blog findBlogById(Long id);

    /**
     * 根据Id获取并转换博客内容
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "cache")
    Blog findAndConvertBlogById(Long id);

    /**
     * 查询首页博客分页列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Cacheable(cacheNames = "cache")
    PageInfo<Blog> findHomeBlogList(Integer currentPage, Integer pageSize);

    /**
     * 查询置顶博客列表
     * @return
     */
    @Cacheable(cacheNames = "cache")
    List<Blog> findTopBlogList();


    /**
     * 查询归档博客列表
     * @return
     */
    @Cacheable(cacheNames = "cache")
    List<Archives> findArchivesBlogList();

    /**
     * 查询最近更新的前top条推荐的博客
     * @param top
     * @return
     */
    @Cacheable(cacheNames = "cache")
    List<Blog> findTopRecommendBlogList(Integer top);

    /**
     * 查询博客搜索结果
     * @param currentPage
     * @param pageSize
     * @param search
     * @return
     */
    @Cacheable(cacheNames = "cache")
    PageInfo<Blog> findSearchBlog(Integer currentPage, Integer pageSize, String search);

    /**
     * 通过标签Id查询博客列表
     * @param currentPage
     * @param pageSize
     * @param tagId
     * @return
     */
    @Cacheable(cacheNames = "cache")
    PageInfo<Blog>  findBlogByTagId(Integer currentPage, Integer pageSize, Long tagId);

    /**
     * 根据分类Id查询博客列表
     * @param currentPage
     * @param pageSize
     * @param typeId
     * @return
     */
    @Cacheable(cacheNames = "cache")
    PageInfo<Blog> findBlogByTypeId(Integer currentPage, Integer pageSize, Long typeId);

//    -----------------------------------------后台功能---------------------------------------------

    /**
     * 查询博客列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Cacheable(cacheNames = "cache")
    PageInfo<Blog> findBlogList(Integer currentPage, Integer pageSize, BlogManageQueryVo blogManageQueryVo);

    /**
     * 保存博客
     * @param blog
     * @return
     */
    @ClearRedisCache(cascade = {TagServiceImpl.class, TypeServiceImpl.class})
    int saveBlog(Blog blog);

    /**
     * 根据id更新博客信息
     * @param blog
     * @return
     */
    @ClearRedisCache(cascade = {TagServiceImpl.class, TypeServiceImpl.class})
    int updateBlog(Blog blog);

    /**
     * 根据id删除博客
     * @param id
     */
    @ClearRedisCache(cascade = {TagServiceImpl.class, TypeServiceImpl.class})
    void deleteBlog(Long id);
}
