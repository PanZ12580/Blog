package com.hzhang.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/10/8 21:12
 * @description：目录
 * @modified By：
 * @version: $
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel("前端目录")
public class Catalog implements Serializable {
    @ApiModelProperty("目录id")
    private Long id;
    @ApiModelProperty("目录跳转链接")
    private String to;
    @ApiModelProperty("目录标题")
    private String title;
    @ApiModelProperty("目录图标")
    private String icon;
    @ApiModelProperty("目录作用域（类型）")
    private Byte scope;
}
