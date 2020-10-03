package com.hzhang.pojo;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"handler"})
@ApiModel("留言实体类")
public class Message implements Serializable, CommonMsg {
    @ApiModelProperty("留言id")
    private Long id;
    @ApiModelProperty("留言创建时间")
    private Long createTime;
    @ApiModelProperty("留言内容")
    private String content;
    @ApiModelProperty("留言标识是否为管理员的留言内容")
    private Boolean adminMessage;
    @ApiModelProperty("父留言的Id")
    private Long parentMsgId;

    @ApiModelProperty("留言用户")
    private User user;
    @ApiModelProperty("该留言的父留言")
    @ToString.Exclude
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Message.class)
    private Message parentMessage;
    @ApiModelProperty("该留言的子留言列表")
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Message> childMessages;

    @ApiModelProperty("该留言下子留言的数目")
    private Integer childMessagesCount;
}
