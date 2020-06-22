package com.hzhang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzhang.dao.TagDao;
import com.hzhang.pojo.Tag;
import com.hzhang.service.TagService;
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
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Long saveTag(Tag tag) {
        Tag tagByName = findTagByName(tag.getName());
        if (tagByName != null) {
            return 0L;
        }
        return tagDao.saveTag(tag);
    }

    @Override
    public Tag findTagById(Long id) {
        return tagDao.findTagById(id);
    }

    @Override
    public Tag findTagByName(String name) {
        return tagDao.findTagByName(name);
    }

    @Override
    public PageInfo<Tag> findTagList(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize, "id desc");
        List<Tag> tagList = tagDao.findTagList();
        tagList.forEach(x -> x.getBlogCount());
        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);
        return pageInfo;
    }

    @Override
    public List<Tag> findTopTagList(Integer top) {
        List<Tag> tagList = tagDao.findTagList();
        Collections.sort(tagList, (a, b) -> b.getBlogCount() - a.getBlogCount());
        if(top > tagList.size()){
            top = tagList.size();
        }
        List<Tag> tags = new ArrayList<>(tagList.subList(0, top));
        return tags;
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagDao.deleteTag(id);
    }
}
