package com.hzhang.controller.profile;

import com.github.pagehelper.PageInfo;
import com.hzhang.annotation.AuthToken;
import com.hzhang.pojo.Result;
import com.hzhang.pojo.Type;
import com.hzhang.service.TypeService;
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
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    @ApiOperation("获取分类列表")
    public Result types(@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<Type> pageInfo = typeService.findTypeList(currentPage, pageSize);
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(pageInfo)
                .build();
    }

    @GetMapping("/findTypeById")
    @ApiOperation("根据id查询分类")
    public Result findTypeById(@RequestParam("id") Long id) {
        Boolean flag = false;
        if(id != null){
            Type type = typeService.findTypeById(id);
            if(type != null) {
                flag = true;
                return Result.builder()
                        .statusCode(HttpStatus.OK.value())
                        .data(type)
                        .flag(flag)
                        .build();
            }
        }
        return Result.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .flag(flag)
                .build();
    }

    @PutMapping("/saveType")
    @ApiOperation("保存分类")
    public Result saveType(@RequestBody Type type) {
        String errorMsg = "服务器发生错误";
        if(type == null || StringUtils.isEmpty(type.getName())) {
            errorMsg = "分类名称不能为空";
        }else {
            Long saveType = typeService.saveType(type);
            if (saveType != null && saveType != 0) {
                return Result.builder()
                        .flag(true)
                        .statusCode(HttpStatus.OK.value())
                        .build();
            }
            if(saveType == 0){
                errorMsg = "分类已存在，无需重复插入";
            }
        }
        return Result.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMsg(errorMsg)
                .build();
    }

    @PutMapping("/updateType")
    @ApiOperation("根据id修改分类")
    public Result updateType(@RequestBody Type type) {
        String errorMsg = "";
        if(type == null || StringUtils.isEmpty(type.getId())) {
            errorMsg = "传入数据有误！";
        }else {
            int updateType = typeService.updateType(type);
            if (updateType != 0) {
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

    @DeleteMapping("/deleteType")
    @ApiOperation("根据id删除分类")
    public Result deleteType(@RequestParam("id") Long id) {
        try {
            typeService.deleteType(id);
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
