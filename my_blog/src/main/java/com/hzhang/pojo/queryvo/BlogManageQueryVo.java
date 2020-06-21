package com.hzhang.pojo.queryvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;


/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/12 14:18
 * @description：后台博客管理界面中，用于封装查询博客列表的条件的实体类
 * @modified By：
 * @version: $
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@ApiModel("博客管理控制器中用到的查询条件实体类")
public class BlogManageQueryVo implements Serializable {
    @ApiModelProperty("当前第几页")
    private Integer currentPage = 1;
    @ApiModelProperty("每页显示条数")
    private Integer pageSize = 5;
    @ApiModelProperty("博客标题")
    private String title = null;
    @ApiModelProperty("博客分类的id")
    private Long typeId = null;
    @ApiModelProperty("博客的推荐状态")
    private Boolean recommend = null;
}
