package com.hzhang.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/5/31 23:48
 * @description：封装返回给前端的信息
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@ApiModel("返回的结果实体类")
@JsonIgnoreProperties(value = {"handler"})
public class Result implements Serializable {
    @ApiModelProperty("标识操作是否成功")
    private boolean flag;
    @ApiModelProperty("状态码")
    private Integer statusCode;
    @ApiModelProperty("错误信息")
    private String errorMsg;
    @ApiModelProperty("目标数据")
    private Object data;
    @ApiModelProperty("返回token")
    private String token;
}
