package com.hzhang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzhang.dao.BlogDao;
import com.hzhang.exception.NotFoundException;
import com.hzhang.pojo.Blog;
import com.hzhang.pojo.Tag;
import com.hzhang.pojo.Type;
import com.hzhang.pojo.queryvo.BlogManageQueryVo;
import com.hzhang.service.BlogService;
import com.hzhang.utils.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/12 10:43
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog findBlogById(Long id) {
        Blog blogById = blogDao.findBlogById(id);
        blogById.getType();
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
    public Blog findAndConvertBlogById(Long id) {
        Blog blogById = blogDao.findBlogById(id);
        if (blogById == null) {
            throw new NotFoundException("博客不存在");
        }
        String content = blogById.getContent();
        String parseContent = MarkdownUtils.markdownToHtmlExtensions(content);
        blogById.setContent(parseContent);
        blogById.getType();
        blogById.getUser();
        blogById.getTagList();
        blogById.getCommentList();
        return blogById;
    }

    @Override
    public PageInfo<Blog> findBlogList(Integer currentPage, Integer pageSize, BlogManageQueryVo blogManageQueryVo) {
        PageHelper.startPage(currentPage, pageSize, "update_time desc");
        List<Blog> blogList = blogDao.findBlogList(blogManageQueryVo);
        blogList.forEach(item -> item.getType());
        return new PageInfo<Blog>(blogList);
    }

    @Override
    public PageInfo<Blog> findHomeBlogList(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize, "create_time desc");
        List<Blog> blogList = blogDao.findHomeBlogList();
        blogList.forEach(item -> {
            item.getType();
            item.getUser();
        });
        return new PageInfo<Blog>(blogList);
    }

    @Override
    public List<Blog> findTopRecommendBlogList(Integer top) {
        return blogDao.findTopRecommendBlogList(top);
    }

    @Override
    public PageInfo<Blog> findSearchBlog(Integer currentPage, Integer pageSize, String search) {
        PageHelper.startPage(currentPage, pageSize);
        List<Blog> searchBlog = blogDao.findSearchBlog(search);
        searchBlog.forEach(item -> {
            item.getType();
            item.getUser();
        });
        return new PageInfo<Blog>(searchBlog);
    }

    @Override
    public PageInfo<Blog> findBlogByTagId(Integer currentPage, Integer pageSize, Long tagId) {
        PageHelper.startPage(currentPage, pageSize);
        List<Blog> blogByTagId = blogDao.findBlogByTagId(tagId);
        blogByTagId.forEach(item -> {
            item.getType();
            item.getUser();
        });
        return new PageInfo<Blog>(blogByTagId);
    }

    @Override
    public PageInfo<Blog> findBlogByTypeId(Integer currentPage, Integer pageSize, Long typeId) {
        PageHelper.startPage(currentPage, pageSize);
        List<Blog> blogByTypeId = blogDao.findBlogByTypeId(typeId);
        blogByTypeId.forEach(item -> {
            item.getType();
            item.getUser();
        });
        return new PageInfo<Blog>(blogByTypeId);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int saveBlog(Blog blog) {
        blog.setCreateTime(System.currentTimeMillis());
        blog.setUpdateTime(System.currentTimeMillis());
        blog.setViews(0);
        int record = blogDao.saveBlog(blog);
        blogDao.saveBlogTag(blog);
        return record;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int updateBlog(Blog blog) {
        Blog blogById = findBlogById(blog.getId());
        if (blogById != null) {
            blog.setUpdateTime(System.currentTimeMillis());
            int record = blogDao.updateBlog(blog);
            blogDao.deleteBlogTag(blog);
            if (blog.getTagList().size() > 0) {
                blogDao.saveBlogTag(blog);
            }
            return record;
        }
        return 0;
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }
}
