package com.hzhang.controller.profile;

import com.github.pagehelper.PageInfo;
import com.hzhang.annotation.AuthToken;
import com.hzhang.pojo.Result;
import com.hzhang.pojo.Tag;
import com.hzhang.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/10 16:50
 * @description：分类管理控制器
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/profile")
@AuthToken
public class ProfileTagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    @ApiOperation("获取分类列表")
    public Result tags(@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<Tag> pageInfo = tagService.findTagList(currentPage, pageSize);
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(pageInfo)
                .build();
    }

    @GetMapping("/findTagById")
    @ApiOperation("根据id查询分类")
    public Result findTagById(@RequestParam("id") Long id) {
        Boolean flag = false;
        if(id != null){
            Tag tag = tagService.findTagById(id);
            if(tag != null) {
                flag = true;
                return Result.builder()
                        .statusCode(HttpStatus.OK.value())
                        .data(tag)
                        .flag(flag)
                        .build();
            }
        }
        return Result.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .flag(flag)
                .build();
    }

    @PutMapping("/saveTag")
    @ApiOperation("保存分类")
    public Result saveTag(@RequestBody Tag tag) {
        String errorMsg = "服务器发生错误";
        if(tag == null || StringUtils.isEmpty(tag.getName())) {
            errorMsg = "分类名称不能为空";
        }else {
            Long saveTag = tagService.saveTag(tag);
            if (saveTag != null && saveTag != 0) {
                return Result.builder()
                        .flag(true)
                        .statusCode(HttpStatus.OK.value())
                        .build();
            }
            if(saveTag == 0){
                errorMsg = "分类已存在，无需重复插入";
            }
        }
        return Result.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMsg(errorMsg)
                .build();
    }

    @PutMapping("/updateTag")
    @ApiOperation("根据id修改分类")
    public Result updateTag(@RequestBody Tag tag) {
        String errorMsg = "";
        if(tag == null || StringUtils.isEmpty(tag.getId())) {
            errorMsg = "传入数据有误！";
        }else {
            int updateTag = tagService.updateTag(tag);
            if (updateTag != 0) {
                return Result.builder()
                        .flag(true)
                        .statusCode(HttpStatus.OK.value())
                        .build();
            }
            else {
                errorMsg = "修改出现异常，修改失败！";
            }
        }
        return Result.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMsg(errorMsg)
                .build();
    }

    @DeleteMapping("/deleteTag")
    @ApiOperation("根据id删除分类")
    public Result deleteTag(@RequestParam("id") Long id) {
        try {
            tagService.deleteTag(id);
            return Result.builder()
                    .flag(true)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return Result.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .errorMsg(e.getMessage())
                    .build();
        }
    }
}
