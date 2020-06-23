package com.hzhang.controller;

import com.hzhang.pojo.Result;
import com.hzhang.pojo.User;
import com.hzhang.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/22 12:18
 * @description：用户控制器类
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAdmin")
    @ApiOperation("获取管理员信息")
    public Result findAdmin() {
        User admin = userService.findAdmin();
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(admin)
                .build();
    }
}
