package com.hzhang.controller;

import com.hzhang.pojo.Blog;
import com.hzhang.pojo.Result;
import com.hzhang.service.BlogService;
import com.hzhang.utils.IpAddressUtils;
import com.hzhang.utils.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/findBlogDetail")
    @ApiOperation("根据博客Id查询博客详情信息")
    public Result findBlogDetail(@RequestParam("id") Long id, HttpServletRequest request){
        Blog blogById = blogService.findAndConvertBlogById(id);
        Long views = pfAddAndCount(blogById, request);
        blogById.setViews(views);
        return Result.builder()
                .statusCode(HttpStatus.OK.value())
                .flag(true)
                .data(blogById)
                .build();
    }

    /**
     * 添加元素并返回访问数量
     * @param blog
     * @return
     */
    public Long pfAddAndCount(Blog blog, HttpServletRequest request) {
        String key = "views::blogId-" + blog.getId();
        String value = IpAddressUtils.getIpAddress(request) + System.currentTimeMillis();
        redisUtil.pfadd(key, value);
        return redisUtil.pfcount(key);
    }
}
