package com.hzhang.controller.profile;

import com.github.pagehelper.PageInfo;
import com.hzhang.annotation.AuthToken;
import com.hzhang.pojo.Blog;
import com.hzhang.pojo.Result;
import com.hzhang.pojo.queryvo.BlogManageQueryVo;
import com.hzhang.service.profile.BlogManageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/12 14:50
 * @description：博客管理控制器
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/profile")
@AuthToken
public class BlogManageController {
    @Autowired
    private BlogManageService blogManageService;

    @PostMapping("/findBlogList")
    @ApiOperation("根据传入条件查询博客列表")
    public Result findBlogList(@RequestBody(required = false)
                               @ApiParam(value = "封装查询条件的实体类",
                                       example = "{title: '', type: {id: '', name: ''}, recommend: ''}")
                                       BlogManageQueryVo blogManageQueryVo) {
        if (blogManageQueryVo == null) {
            blogManageQueryVo = new BlogManageQueryVo();
        }
        PageInfo<Blog> blogList = blogManageService.findBlogList(
                blogManageQueryVo.getCurrentPage(),
                blogManageQueryVo.getPageSize(),
                blogManageQueryVo
        );
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(blogList)
                .build();
    }

    @GetMapping("/findBlogById")
    @ApiOperation("根据id查找博客")
    public Result findBlogById(@RequestParam("id") Long id){
        String errorMsg = "指定博客不存在";
        Blog blogById = blogManageService.findBlogById(id);
        if(blogById != null){
            return Result.builder()
                    .flag(true)
                    .statusCode(HttpStatus.OK.value())
                    .data(blogById)
                    .build();
        }
        return Result.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMsg(errorMsg)
                .build();
    }

    @PutMapping("/saveBlog")
    @ApiOperation("保存博客")
    public Result saveBlog(@RequestBody Blog blog) {
        String errorMsg = "服务器发生错误";
        if (blog != null) {
            int record = blogManageService.saveBlog(blog);
            if (record != 0) {
                return Result.builder()
                        .statusCode(HttpStatus.OK.value())
                        .flag(true)
                        .build();
            }
        }
        else{
            errorMsg = "数据有误，博客不能为空";
        }
        return Result.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMsg(errorMsg)
                .build();
    }

    @PutMapping("/updateBlog")
    @ApiOperation("更新指定博客")
    public Result updateBlog(@RequestBody Blog blog){
        String errorMsg = "服务器发生错误";
        if (blog != null) {
            int record = blogManageService.updateBlog(blog);
            if (record != 0) {
                return Result.builder()
                        .statusCode(HttpStatus.OK.value())
                        .flag(true)
                        .build();
            }
        }
        return Result.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMsg(errorMsg)
                .build();
    }

    @DeleteMapping("/deleteBlog")
    @ApiOperation("根据id删除指定博客")
    public Result deleteBlog(@RequestParam("id") Long id){
        blogManageService.deleteBlog(id);
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
