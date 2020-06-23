package com.hzhang.controller;

import com.hzhang.pojo.Blog;
import com.hzhang.pojo.Result;
import com.hzhang.service.BlogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/23 14:55
 * @description：博客详情页面控制器
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/findBlogDetail")
    @ApiOperation("根据博客Id查询博客详情信息")
    public Result findBlogDetail(@RequestParam("id") Long id){
        Blog blogById = blogService.findAndConvertBlogById(id);
        return Result.builder()
                .statusCode(HttpStatus.OK.value())
                .flag(true)
                .data(blogById)
                .build();
    }
}
