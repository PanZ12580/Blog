package com.hzhang.controller;

import com.github.pagehelper.PageInfo;
import com.hzhang.pojo.Blog;
import com.hzhang.pojo.Result;
import com.hzhang.pojo.Tag;
import com.hzhang.service.BlogService;
import com.hzhang.service.TagService;
import com.hzhang.utils.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/22 11:33
 * @description：标签控制器
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/findTagList")
    @ApiOperation("获取标签列表")
    public Result findTagList() {
        PageInfo<Tag> tagList = tagService.findTagList(1, 0);
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(tagList)
                .build();
    }

    @GetMapping("/findBlogByTagId")
    @ApiOperation("通过标签Id查询博客")
    public Result findBlogByTagId(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                  @RequestParam(value = "tagId", defaultValue = "0") Long tagId){
        PageInfo<Blog> blogByTagId = blogService.findBlogByTagId(currentPage, pageSize, tagId);
        blogByTagId.getList().forEach(blog -> {
            String key = "views::blogId-" + blog.getId();
            blog.setViews(redisUtil.pfcount(key));
        });
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(blogByTagId)
                .build();
    }
}
