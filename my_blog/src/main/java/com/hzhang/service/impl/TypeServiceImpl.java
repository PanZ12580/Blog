package com.hzhang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzhang.dao.TypeDao;
import com.hzhang.pojo.Type;
import com.hzhang.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/10 15:44
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Long saveType(Type type) {
        Type typeByName = findTypeByName(type.getName());
        if (typeByName != null) {
            return 0L;
        }
        return typeDao.saveType(type);
    }

    @Override
    public Type findTypeById(Long id) {
        return typeDao.findTypeById(id);
    }

    @Override
    public Type findTypeByName(String name) {
        return typeDao.findTypeByName(name);
    }

    @Override
    public PageInfo<Type> findTypeList(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize, "id desc");
        PageInfo<Type> pageInfo = new PageInfo<>(typeDao.findTypeList());
        return pageInfo;
    }

    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }
}
