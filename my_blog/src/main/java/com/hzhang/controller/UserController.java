package com.hzhang.controller;

import com.hzhang.pojo.Result;
import com.hzhang.pojo.User;
import com.hzhang.service.UserService;
import com.hzhang.utils.IpAddressUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/findUserMsg")
    @ApiOperation("获取用户信息")
    public Result findUserMsg(HttpServletRequest request) {
//        通过token获取
        String token = request.getHeader("token");
        if(!StringUtils.isEmpty(token)){
            User userByToken = userService.findUserByToken(token);
            if(userByToken != null){
                return Result.builder()
                        .flag(true)
                        .statusCode(HttpStatus.OK.value())
                        .data(userByToken)
                        .build();
            }
        }
//        通过ip地址获取
        String ipAddress = IpAddressUtils.getIpAddress(request);
        User userByIp = userService.findUserByIp(ipAddress);
        return Result.builder()
                .flag(true)
                .statusCode(HttpStatus.OK.value())
                .data(userByIp)
                .build();
    }
}
