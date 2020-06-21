package com.hzhang.controller.profile;

import com.hzhang.pojo.Result;
import com.hzhang.pojo.User;
import com.hzhang.service.profile.UserService;
import com.hzhang.utils.RedisUtil;
import com.hzhang.utils.tokenUtils.Const;
import com.hzhang.utils.tokenUtils.TokenGenerator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/1 15:00
 * @description：后台个人中心登录控制器
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/profile")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    @ApiOperation(value = "登录方法")
    public Result login(@RequestParam @ApiParam(value = "用户名", required = true) String username,
                        @RequestParam @ApiParam(value = "密码", required = true) String password) {
        boolean flag = false;
        String errorMsg = "用户名或密码错误！";
        String token = "";
        User user = null;
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            user = userService.findUser(username, password);
            if (user != null) {
                token = tokenGenerator.generate(username, password);
//            绑定token与username
                redisUtil.set(username, token);
//            设置过期时间
                redisUtil.expire(username, Const.TOKEN_EXPIRE_TIME);
//                绑定token与user
                redisUtil.set(token, user);
                redisUtil.expire(token, Const.TOKEN_EXPIRE_TIME);
                Long currentTime = System.currentTimeMillis();
//                绑定创建时间
                redisUtil.set(token + username, currentTime.toString());
                flag = true;
                errorMsg = "";
                user.setPassword(null);
            }
        }
        return Result.builder()
                .flag(flag)
                .errorMsg(errorMsg)
                .statusCode(200)
                .data(user)
                .token(token)
                .build();
    }

    @GetMapping("/logout")
    @ApiOperation(value = "登出方法")
    public Result logout(HttpServletRequest request) {
        boolean flag = false;
        Integer statusCode = HttpStatus.UNAUTHORIZED.value();
        try {
            String token = request.getHeader("token");
            if (!StringUtils.isEmpty(token)) {
                User user = (User) redisUtil.get(token);
                if (user != null) {
                    redisUtil.del(token, user.getUsername(), token + user.getUsername());
                    flag = true;
                    statusCode = HttpStatus.OK.value();
                }
            }
            return Result.builder()
                    .flag(flag)
                    .statusCode(statusCode)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            return Result.builder()
                    .flag(flag)
                    .statusCode(statusCode)
                    .errorMsg(e.getMessage())
                    .build();
        }
    }

    @GetMapping("/findUserByToken")
    @ApiOperation("通过token获得用户信息")
    public Result findUserByToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        boolean flag = false;
        Integer statusCode = HttpStatus.UNAUTHORIZED.value();
        if (StringUtils.isEmpty(token)) {
            return Result.builder()
                    .flag(flag)
                    .statusCode(statusCode)
                    .errorMsg("token不存在")
                    .build();
        }
        User user = (User) redisUtil.get(token);
        if (user != null) {
            flag = true;
            statusCode = HttpStatus.OK.value();
            return Result.builder()
                    .flag(flag)
                    .statusCode(statusCode)
                    .data(user)
                    .build();
        }
        return Result.builder()
                .flag(flag)
                .statusCode(statusCode)
                .errorMsg("token过期或错误")
                .build();
    }
}
