package com.hzhang.service;

import com.hzhang.pojo.User;
import org.springframework.cache.annotation.Cacheable;

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
}
