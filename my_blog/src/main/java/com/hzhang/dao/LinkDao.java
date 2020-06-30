package com.hzhang.dao;

import com.hzhang.pojo.Link;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/30 19:29
 * @description：友情链接Dao层
 * @modified By：
 * @version: $
 */
@Mapper
@Repository
public interface LinkDao {
    /**
     * 查找友情链接列表
     * @return
     */
    List<Link> findLinkList();

    /**
     * 查找已允许的友情链接列表
     * @return
     */
    List<Link> findAllowedLinkList();

    /**
     * 保存友情链接
     * @param link
     * @return
     */
    Integer saveLink(Link link);

    /**
     * 更新友情链接
     * @param link
     * @return
     */
    Integer updateLink(Link link);

    /**
     * 根据id删除友情链接
     * @param id
     */
    void deleteLink(Long id);
}
