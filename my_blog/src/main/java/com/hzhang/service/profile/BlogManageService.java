package com.hzhang.service.profile;

import com.github.pagehelper.PageInfo;
import com.hzhang.annotation.ClearRedisCache;
import com.hzhang.pojo.Blog;
import com.hzhang.pojo.queryvo.BlogManageQueryVo;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/12 10:38
 * @description：后台博客列表管理
 * @modified By：
 * @version: $
 */
public interface BlogManageService {
    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "cache")
    Blog findBlogById(Long id);

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
    @ClearRedisCache
    int saveBlog(Blog blog);

    /**
     * 根据id更新博客信息
     * @param blog
     * @return
     */
    @ClearRedisCache
    int updateBlog(Blog blog);

    /**
     * 根据id删除博客
     * @param id
     */
    @ClearRedisCache
    void deleteBlog(Long id);
}
