package com.hzhang.service.profile.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzhang.dao.profile.BlogManageDao;
import com.hzhang.pojo.Blog;
import com.hzhang.pojo.Tag;
import com.hzhang.pojo.queryvo.BlogManageQueryVo;
import com.hzhang.service.profile.BlogManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/12 10:43
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class BlogManageServiceImpl implements BlogManageService {
    @Autowired
    private BlogManageDao blogManageDao;

    @Override
    public Blog findBlogById(Long id) {
        Blog blogById = blogManageDao.findBlogById(id);
        List<Tag> tagList = blogById.getTagList();
        List<Long> tagIds = new ArrayList<>();
        if (tagList.size() > 0) {
            for (Tag tag : tagList) {
                tagIds.add(tag.getId());
            }
        }
        blogById.setTagIds(tagIds);
        return blogById;
    }

    @Override
    public PageInfo<Blog> findBlogList(Integer currentPage, Integer pageSize, BlogManageQueryVo blogManageQueryVo) {
        PageHelper.startPage(currentPage, pageSize, "update_time desc");
        List<Blog> blogList = blogManageDao.findBlogList(blogManageQueryVo);
        return new PageInfo<Blog>(blogList);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int saveBlog(Blog blog) {
        blog.setCreateTime(System.currentTimeMillis());
        blog.setUpdateTime(System.currentTimeMillis());
        blog.setViews(0);
        int record = blogManageDao.saveBlog(blog);
        blogManageDao.saveBlogTag(blog);
        return record;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int updateBlog(Blog blog) {
        Blog blogById = findBlogById(blog.getId());
        if (blogById != null) {
            blog.setUpdateTime(System.currentTimeMillis());
            int record = blogManageDao.updateBlog(blog);
            blogManageDao.deleteBlogTag(blog);
            if (blog.getTagList().size() > 0) {
                blogManageDao.saveBlogTag(blog);
            }
            return record;
        }
        return 0;
    }

    @Override
    public void deleteBlog(Long id) {
        blogManageDao.deleteBlog(id);
    }
}
