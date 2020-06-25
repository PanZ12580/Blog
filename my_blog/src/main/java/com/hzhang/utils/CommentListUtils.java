package com.hzhang.utils;

import com.hzhang.pojo.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/24 22:27
 * @description：处理评论的层级关系
 * @modified By：
 * @version: $
 */
@Component
public class CommentListUtils {
    /**
     * 找到所有没有父评论的留言
     *
     * @param commentList
     * @return
     */
    public static List<Comment> dealCommentList(List<Comment> commentList) {
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
    public static List<Comment> getChildComment(Comment comment, List<Comment> commentList) {
        ArrayDeque<Comment> queue = new ArrayDeque<>();
        List<Comment> childList = new ArrayList<>();
        queue.addLast(comment);
        while (!queue.isEmpty()) {
            Comment tmpParent = queue.removeFirst();
            for (Comment c : commentList) {
                if (c.getParentCommentId() != null && c.getParentCommentId().equals(tmpParent.getId())) {
                    Comment tmp = new Comment();
                    tmp.setId(tmpParent.getParentCommentId());
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
