package com.hzhang.controller;

import com.github.pagehelper.PageInfo;
import com.hzhang.pojo.Blog;
import com.hzhang.pojo.Result;
import com.hzhang.pojo.Type;
import com.hzhang.service.BlogService;
import com.hzhang.service.TypeService;
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
 * @description：分类控制器
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/findTypeList")
    @ApiOperation("获取分类列表")
    public Result findTypeList() {
        PageInfo<Type> typeList = typeService.findTypeList(1, 0);
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(typeList)
                .build();
    }

    @GetMapping("/findBlogByTypeId")
    @ApiOperation("通过分类Id查询博客")
    public Result findBlogByTypeId(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                  @RequestParam(value = "typeId", defaultValue = "0") Long typeId){
        PageInfo<Blog> blogByTypeId = blogService.findBlogByTypeId(currentPage, pageSize, typeId);
        blogByTypeId.getList().forEach(blog -> {
            String key = "views::blogId-" + blog.getId();
            blog.setViews(redisUtil.pfcount(key));
        });
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(blogByTypeId)
                .build();
    }
}
