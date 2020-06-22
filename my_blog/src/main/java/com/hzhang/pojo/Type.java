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
 * @date ：Created in 2020/6/1 0:28
 * @description：博客分类信息
 * @modified By：
 * @version: $
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@ApiModel("分类实体类")
@JsonIgnoreProperties(value = {"handler"})
public class Type implements Serializable {
    @ApiModelProperty("分类id")
    private Long id;
    @ApiModelProperty("分类名称")
    private String name;

    @JacksonXmlProperty(localName = "blogList")
    @JacksonXmlElementWrapper(useWrapping = false)
    @ApiModelProperty("该分类下的博客列表")
    private List<Blog> blogList;

    @ApiModelProperty("该分类下具有的博客数量")
    private Integer blogCount;
}
