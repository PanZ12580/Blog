package com.hzhang.dao.profile;

import com.hzhang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findUser(@Param("username") String username, @Param("password") String password);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById(Long id);
}
