package com.hzhang.service.impl;

import com.hzhang.dao.CatalogDao;
import com.hzhang.pojo.Catalog;
import com.hzhang.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/10/8 21:27
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private CatalogDao catalogDao;

    @Override
    public List<Catalog> findCatalogListByScope(Byte scope) {
        return catalogDao.findCatalogListByScope(scope);
    }
}
