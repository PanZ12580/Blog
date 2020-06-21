package com.hzhang.service.profile;

import com.hzhang.pojo.User;

public interface UserService {
    /**
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    User findUser(String username, String password);

}
