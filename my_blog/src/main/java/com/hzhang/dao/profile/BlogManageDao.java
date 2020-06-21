package com.hzhang.dao.profile;

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
public interface BlogManageDao {
    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    Blog findBlogById(Long id);

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
