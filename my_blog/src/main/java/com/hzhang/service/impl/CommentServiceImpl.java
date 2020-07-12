package com.hzhang.service.impl;

import com.hzhang.dao.CommentDao;
import com.hzhang.dao.UserDao;
import com.hzhang.pojo.Comment;
import com.hzhang.pojo.User;
import com.hzhang.service.CommentService;
import com.hzhang.utils.IpAddressUtils;
import com.hzhang.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/24 15:34
 * @description：博客评论业务逻辑层实现
 * @modified By：
 * @version: $
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${comment.avatar}")
    private String avatar;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Comment> findCommentListByBlogId(Long id) {
        List<Comment> commentList = commentDao.findCommentListByBlogId(id);
        commentList.forEach(x -> x.getUser());
        List<Comment> newCommentList = dealCommentList(commentList);
        return newCommentList;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int saveComment(Comment comment, HttpServletRequest request) {
        comment.setCreateTime(System.currentTimeMillis());
//        获取客户端ip地址
        String ipAddress = IpAddressUtils.getIpAddress(request);
//        从请求头中获取token信息
        String token = request.getHeader("token");
        if(!StringUtils.isEmpty(token)){
            User user = (User) redisUtil.get(token);
            if(user != null){
//                token校验通过则为管理员评论
                comment.getUser().setId(user.getId());
                comment.setAdminComment(true);
                userDao.saveUser(comment.getUser());
                return commentDao.saveComment(comment);
            }
        }
//        普通用户评论，根据ip地址获取用户信息
        User userByIp = userDao.findUserByIp(ipAddress);
//        判断用户是否存在
        if(userByIp != null){
            comment.getUser().setId(userByIp.getId());
        }
        else{
            comment.getUser().setAvatar(avatar);
            comment.getUser().setIp(ipAddress);
            comment.getUser().setType(0);
            comment.getUser().setCreateTime(System.currentTimeMillis());
            comment.getUser().setUpdateTime(System.currentTimeMillis());
        }
        userDao.saveUser(comment.getUser());
        return commentDao.saveComment(comment);
    }

    /**
     * 找到所有没有父评论的留言
     *
     * @param commentList
     * @return
     */
    public List<Comment> dealCommentList(List<Comment> commentList) {
        List<Comment> newCommentList = new ArrayList<>();
        for (Comment comment : commentList) {
            if (comment.getParentCommentId() == null) {
                newCommentList.add(comment);
                List<Comment> childList = getChildComment(comment, commentList);
                comment.setChildComments(childList);
            }
        }
        return newCommentList;
    }

    /**
     * 对顶级留言进行处理，广度优先搜索，逐个加入链表，最后按时间降序排序后返回
     *
     * @param comment
     * @param commentList
     * @return
     */
    public List<Comment> getChildComment(Comment comment, List<Comment> commentList) {
        ArrayDeque<Comment> queue = new ArrayDeque<>();
        List<Comment> childList = new ArrayList<>();
        queue.addLast(comment);
        while (!queue.isEmpty()) {
            Comment tmpParent = queue.removeFirst();
            for (Comment c : commentList) {
                if (c.getParentCommentId() != null && c.getParentCommentId().equals(tmpParent.getId())) {
                    Comment tmp = new Comment();
                    tmp.setId(tmpParent.getId());
                    tmp.setUser(tmpParent.getUser());
                    c.setParentComment(tmp);
                    childList.add(c);
                    queue.addLast(c);
                }
            }
        }
        Collections.sort(childList, (a, b) -> (int) (b.getCreateTime() - a.getCreateTime()));
        return childList;
    }
}
