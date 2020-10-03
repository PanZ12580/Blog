package com.hzhang.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/9/25 16:05
 * @description： 邮件对象
 * @modified By：
 * @version: $
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@ApiModel("邮件对象")
public class Mail {
    @ApiModelProperty("对应的消息id")
    private String msgId;
    @ApiModelProperty("接收方")
    private String to;
    @ApiModelProperty("发送方")
    private String subject;
    @ApiModelProperty("邮件内容")
    private String content;
    @ApiModelProperty("重发次数")
    private Integer retryCount = 0;
}
