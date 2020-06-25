package com.hzhang.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
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
 * @date ：Created in 2020/6/1 0:22
 * @description：博客信息
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler"})
@ApiModel("博客实体类")
public class Blog implements Serializable {
    @ApiModelProperty("博客id")
    private Long id;
    @ApiModelProperty("博客标题")
    private String title;
    @ApiModelProperty("博客内容")
    private String content;
    @ApiModelProperty("博客描述")
    private String description;
    @ApiModelProperty("博客首图")
    private String firstPicture;
    @ApiModelProperty("博客标记（原创、转载、翻译）")
    private String flag;
    @ApiModelProperty("博客浏览次数")
    private Long views;
    @ApiModelProperty("博客是否开放赞赏")
    private boolean appreciation;
    @ApiModelProperty("博客是否允许转载")
    private boolean shareStatement;
    @ApiModelProperty("博客是否允许评论")
    private boolean commentabled;
    @ApiModelProperty("博客是否公开")
    private boolean published;
    @ApiModelProperty("是否推荐该博客")
    private boolean recommend;
    @ApiModelProperty("博客创建的时间")
    private Long createTime;
    @ApiModelProperty("博客更新的时间")
    private Long updateTime;

    @JacksonXmlProperty(localName = "type")
    @JacksonXmlElementWrapper(useWrapping = false)
    @ApiModelProperty("博客所处分类")
    private Type type;
    @JacksonXmlProperty(localName = "tagList")
    @JacksonXmlElementWrapper(useWrapping = false)
    @ApiModelProperty("博客对应的标签列表")
    private List<Tag> tagList;
    @ApiModelProperty("发表该博客的用户")
    private User user;
    @ApiModelProperty("博客下的评论列表")
    private List<Comment> commentList;

    @ApiModelProperty("博客对应的标签的id")
    private List<Long> tagIds;
}
