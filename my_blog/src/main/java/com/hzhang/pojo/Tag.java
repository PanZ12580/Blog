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
 * @date ：Created in 2020/6/1 0:28
 * @description：标签信息
 * @modified By：
 * @version: $
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@ApiModel("标签实体类")
@JsonIgnoreProperties(value = {"handler"})
public class Tag implements Serializable {
    @ApiModelProperty("标签id")
    private Long id;
    @ApiModelProperty("标签名称")
    private String name;

    @ApiModelProperty("该标签下的博客列表")
    private List<Blog> blogList;

    @ApiModelProperty("该标签下的博客数目")
    private Integer blogCount;
}
