package com.hzhang.service.profile.impl;

import com.hzhang.dao.profile.UserDao;
import com.hzhang.pojo.User;
import com.hzhang.service.profile.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/1 13:36
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User findUser(String username, String password) {
        return userDao.findUser(username, password);
    }

}
