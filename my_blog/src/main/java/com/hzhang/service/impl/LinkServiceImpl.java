package com.hzhang.service.impl;

import com.hzhang.dao.LinkDao;
import com.hzhang.pojo.Link;
import com.hzhang.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/30 20:20
 * @description：友情链接业务逻辑层实现
 * @modified By：
 * @version: $
 */
@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkDao linkDao;

    @Override
    public List<Link> findLinkList() {
        return linkDao.findLinkList();
    }

    @Override
    public List<Link> findAllowedLinkList() {
        return linkDao.findAllowedLinkList();
    }

    @Override
    public Integer saveLink(Link link) {
        if (!validate(link)) {
            throw new NullPointerException("传入参数非空校验不通过");
        }
        link.setCreateTime(System.currentTimeMillis());
        Integer saveLink = linkDao.saveLink(link);
        if (saveLink < 1) {
            throw new RuntimeException("友情链接上传失败");
        }
        return saveLink;
    }

    @Override
    public Integer updateLink(Link link) {
        Integer updateLink = linkDao.updateLink(link);
        if (updateLink < 1) {
            throw new RuntimeException("友情链接上传失败");
        }
        return updateLink;
    }

    @Override
    public void deleteLink(Long id) {
        linkDao.deleteLink(id);
    }

    public Boolean validate(Link link) {
        return !StringUtils.isEmpty(link.getUrl()) &&
                !StringUtils.isEmpty(link.getName()) &&
                !StringUtils.isEmpty(link.getPicture());
    }
}
