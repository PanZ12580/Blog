package com.hzhang.pojo;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/1 0:31
 * @description：博客中的评论信息
 * @modified By：
 * @version: $
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler"})
@ApiModel("评论实体类")
public class Comment implements Serializable, CommonMsg {
    @ApiModelProperty("评论id")
    @JsonIdentityReference(alwaysAsId = true)
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
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Comment> childComments;
    @ApiModelProperty("父评论")
    @ToString.Exclude
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Comment.class)
    private Comment parentComment;
    @ApiModelProperty("评论的用户")
    private User user;
}
