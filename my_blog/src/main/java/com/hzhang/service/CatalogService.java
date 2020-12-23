package com.hzhang.service;

import com.hzhang.pojo.Catalog;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/10/8 21:26
 * @description：目录业务逻辑层
 * @modified By：
 * @version: $
 */
public interface CatalogService {
    /**
     * 根据目录作用域查询目录列表
     * @param scope
     * @return
     */
    @Cacheable(cacheNames = "cache")
    List<Catalog> findCatalogListByScope(@Param("scope") Byte scope);
}
