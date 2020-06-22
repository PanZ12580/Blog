package com.hzhang.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/21 12:35
 * @description：博客标签实体的中间类
 * @modified By：
 * @version: $
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@ApiModel("博客标签实体的中间类")
public class BlogTag implements Serializable {
    @ApiModelProperty("博客Id")
    private Long blogId;
    @ApiModelProperty("标签Id")
    private Long tagId;

    @ApiModelProperty("该类对应的博客")
    private Blog blog;
    @ApiModelProperty("该类对应的标签")
    private Tag tag;
    @ApiModelProperty("某个标签对应的博客数量")
    private Integer blogCount;
}
