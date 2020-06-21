package com.hzhang.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzhang.annotation.AuthToken;
import com.hzhang.pojo.Result;
import com.hzhang.pojo.User;
import com.hzhang.utils.RedisUtil;
import com.hzhang.utils.tokenUtils.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/1 23:58
 * @description：登录权限验证拦截器
 * @modified By：
 * @version: $
 */
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
//        获取被拦截的方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
//        检查是否打上了AuthToken的注解
        if (method.isAnnotationPresent(AuthToken.class) || handlerMethod.getBeanType().isAnnotationPresent(AuthToken.class)) {
//            从请求头取出token
            String token = request.getHeader("token");
            logger.info("Token in the request header: {}", token);
            String username = "";
            if (!StringUtils.isEmpty(token)) {
                User user = (User) redisUtil.get(token);
                if(user != null){
                    username = user.getUsername();
                    logger.info("Username in redis with token is {}", username);
                }
            }
            if (!StringUtils.isEmpty(username)) {
                Long createTime = Long.valueOf(String.valueOf(redisUtil.get(token + username)));
                logger.info("CreateTime of this token is {}", createTime);
                Long liveTime = System.currentTimeMillis() - createTime;
                logger.info("The token has been lived {} seconds", liveTime);
//                存活时间大于限定的时间则重置过期时间
                if (liveTime > Const.TOKEN_RESET_TIME) {
                    redisUtil.expire(username, Const.TOKEN_EXPIRE_TIME);
                    redisUtil.expire(token, Const.TOKEN_EXPIRE_TIME);
                    logger.info("Successful to update expire time!");
                    redisUtil.set(token + username, System.currentTimeMillis());
                }
                return true;
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                Result result = Result.builder()
                        .flag(false)
                        .statusCode(response.getStatus())
                        .errorMsg("认证失败，请先进行登录！")
                        .build();
                ObjectMapper om = new ObjectMapper();
                String jsonResult = om.writeValueAsString(result);
                response.setContentType("json/application;charset=utf8");
                response.getWriter().println(jsonResult);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
