package com.hzhang.dao;

import com.hzhang.pojo.Catalog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/10/8 21:15
 * @description：
 * @modified By：
 * @version: $
 */
@Mapper
@Repository
public interface CatalogDao {
    /**
     * 根据目录作用域查询目录列表
     * @param scope
     * @return
     */
    List<Catalog> findCatalogListByScope(@Param("scope") Byte scope);
}
