package com.hzhang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzhang.dao.TypeDao;
import com.hzhang.pojo.Type;
import com.hzhang.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        List<Type> typeList = typeDao.findTypeList();
        typeList.forEach(x -> x.getBlogCount());
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        return pageInfo;
    }

    @Override
    public List<Type> findTopTypeList(Integer top) {
        List<Type> typeList = typeDao.findTypeList();
        Collections.sort(typeList, (a, b) -> b.getBlogCount() - a.getBlogCount());
        if(top > typeList.size()){
            top = typeList.size();
        }
        List<Type> types = new ArrayList<>(typeList.subList(0, top));
        return types;
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
