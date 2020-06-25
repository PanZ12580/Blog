package com.hzhang.service.impl;

import com.hzhang.dao.UserDao;
import com.hzhang.pojo.User;
import com.hzhang.service.UserService;
import com.hzhang.utils.IpAddressUtils;
import com.hzhang.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

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
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User findUser(String username, String password) {
        return userDao.findUser(username, password);
    }

    @Override
    public User findAdmin() {
        return userDao.findAdmin();
    }

    @Override
    public User findUserByIp(String ip) {
        return userDao.findUserByIp(ip);
    }

    @Override
    public User findUserByToken(String token) {
        return (User) redisUtil.get(token);
    }

}
