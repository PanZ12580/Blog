package com.hzhang.dao;

import com.hzhang.pojo.Blog;
import com.hzhang.pojo.queryvo.BlogManageQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/12 10:43
 * @description：后台博客列表管理
 * @modified By：
 * @version: $
 */
@Repository
@Mapper
public interface BlogDao {
    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    Blog findBlogById(Long id);

    /**
     * 根据分类Id查询博客列表
     * @param typeId
     * @return
     */
    List<Blog> findBlogByTypeId(@Param("typeId") Long typeId);

    /**
     * 根据分类Id查询该分类下具有的博客数目
     * @param typeId
     * @return
     */
    Integer findCountByTypeId(@Param("typeId") Long typeId);


    /**
     * 查询首页展示的博客列表
     * @return
     */
    List<Blog> findHomeBlogList();

    /**
     * 查询归档页博客列表
     * @return
     */
    List<Blog> findArchivesBlogList();

    /**
     * 查询最近更新的前top条推荐的博客
     * @param top
     * @return
     */
    List<Blog> findTopRecommendBlogList(@Param("top") Integer top);

    /**
     * 查询博客搜索结果
     * @param search
     * @return
     */
    List<Blog> findSearchBlog(@Param("search") String search);

    /**
     * 通过标签Id查询博客列表
     * @param tagId
     * @return
     */
    List<Blog> findBlogByTagId(@Param("tagId") Long tagId);

//    --------------------------------------------后台功能--------------------------------------------------

    /**
     * 根据查询条件（标题、分类、是否推荐）来查询博客列表
     * @param blogManageQueryVo
     * @return
     */
    List<Blog> findBlogList(BlogManageQueryVo blogManageQueryVo);

    /**
     * 保存博客
     * @param blog
     * @return
     */
    int saveBlog(Blog blog);

    /**
     * 根据id更新博客信息
     * @param blog
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * 根据id删除博客
     * @param id
     */
    void deleteBlog(Long id);

    /**
     * 保存博客与标签的关联数据
     * @param blog
     */
    void saveBlogTag(Blog blog);

    /**
     * 删除博客与标签的关联数据
     * @param blog
     */
    void deleteBlogTag(Blog blog);

}
