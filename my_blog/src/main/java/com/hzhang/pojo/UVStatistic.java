package com.hzhang.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/29 12:31
 * @description：独立访客统计数据封装
 * @modified By：
 * @version: $
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@JsonIgnoreProperties(value = {"handler"})
@ApiModel("独立访客统计数据封装实体类")
public class UVStatistic implements Serializable {
    @ApiModelProperty("统计数据序列，key: 日期, value: 数量")
    private LinkedHashMap<String, Long> statisticMap;
    @ApiModelProperty("历史访问总数")
    private Long total;
}
