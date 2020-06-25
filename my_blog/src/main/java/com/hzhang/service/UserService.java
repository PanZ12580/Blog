package com.hzhang.service;

import com.hzhang.pojo.User;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author Hzhang
 */
public interface UserService {
    /**
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    User findUser(String username, String password);

    /**
     * 获取网站管理员用户
     * @return
     */
    @Cacheable(cacheNames = "cache")
    User findAdmin();

    /**
     * 通过ip地址获取用户信息
     * @param ip
     * @return
     */
    @Cacheable(cacheNames = "cache")
    User findUserByIp(String ip);

    /**
     * 通过token获取用户（管理员）信息
     * @param token
     * @return
     */
    @Cacheable(cacheNames = "cache")
    User findUserByToken(String token);
}
