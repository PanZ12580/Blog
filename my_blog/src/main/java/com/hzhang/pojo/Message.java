package com.hzhang.pojo;

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
 * @date ：Created in 2020/6/1 14:51
 * @description：留言板块的留言信息
 * @modified By：
 * @version: $
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@ApiModel("留言实体类")
public class Message implements Serializable {
    @ApiModelProperty("留言id")
    private Integer id;
    @ApiModelProperty("留言创建时间")
    private Long createTime;
    @ApiModelProperty("留言内容")
    private String content;
    @ApiModelProperty("留言标识是否为管理员的留言内容")
    private Boolean adminMessage;

    @ApiModelProperty("留言用户")
    private User user;
    @ApiModelProperty("该留言的父留言")
    private Message parentMessage;
    @ApiModelProperty("该留言的子留言列表")
    private List<Message> childMessages;
}
