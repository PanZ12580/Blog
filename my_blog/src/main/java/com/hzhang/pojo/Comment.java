package com.hzhang.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/1 0:31
 * @description：博客中的评论信息
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("评论实体类")
@JsonIgnoreProperties(value = {"handler"})
public class Comment implements Serializable {
    @ApiModelProperty("评论id")
    private Long id;
    @ApiModelProperty("评论内容")
    private String content;
    @ApiModelProperty("标识是否为管理员的评论")
    private Boolean adminComment;
    @ApiModelProperty("评论创建时间")
    private Long createTime;
    @ApiModelProperty("父评论id")
    private Long parentCommentId;
    @ApiModelProperty("博客Id")
    private Long blogId;

    @ApiModelProperty("是哪一条博客下的评论")
    private Blog blog;
    @ApiModelProperty("子评论列表")
    private List<Comment> childComments;
    @ApiModelProperty("父评论")
    private Comment parentComment;
    @ApiModelProperty("评论的用户")
    private User user;
}
