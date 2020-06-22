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
 * @date ：Created in 2020/6/1 0:36
 * @description：用户信息
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("用户实体类")
@JsonIgnoreProperties(value = {"handler"})
public class User implements Serializable {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("用户昵称")
    private String nickname;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户头像的url")
    private String avatar;
    @ApiModelProperty("用户类型")
    private Integer type;
    @ApiModelProperty("用户信息的创建时间")
    private Long createTime;
    @ApiModelProperty("用户信息的更新时间")
    private Long updateTime;
    @ApiModelProperty("用户的ip地址")
    private String ip;
    @ApiModelProperty("用户微信支付二维码")
    private String wechatPay;
    @ApiModelProperty("用户的支付宝支付二维码")
    private String alipay;
    @ApiModelProperty("用户QQ")
    private String qq;
    @ApiModelProperty("用户微信")
    private String wechat;
    @ApiModelProperty("用户微信二维码地址")
    private String wechatCode;

    @ApiModelProperty("用户所发布的博客")
    private List<Blog> blogList;
}
