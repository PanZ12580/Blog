<template>
  <div id="main" class="m-padding-tb-max m-container-small">
    <div class="ui container">
      <manage-header @search="search"></manage-header>
      <manage-table
        :tableHeader="tableHeader"
        :tableList="blogList"
        :isFirstPage="isFirstPage"
        :isLastPage="isLastPage"
        @toLastPage="toLastPage"
        @toNextPage="toNextPage"
        :currentPage="blogManageQueryVo.currentPage"
        :pageSize="blogManageQueryVo.pageSize"
        @edit="edit"
        @remove="remove"
        :toOps="toOps"
      ></manage-table>
    </div>
    <confirm-modal :confirmMsg="confirmMsg" ref="confirm"></confirm-modal>
  </div>
</template>

<script>

import { formatDate } from "common/utils";
import { findBlogList, deleteBlog } from "network/profileAjax";

export default {
  name: "BlogManage",
  data() {
    return {
      tableHeader: ["", "标题", "类型", "推荐", "置顶", "状态", "更新时间", "操作"],
      blogList: [],
      blogManageQueryVo: {
        currentPage: 1,
        pageSize: 5
      },
      isFirstPage: true,
      isLastPage: false,
      prePage: 0,
      nextPage: 0,
      toOps: {
        to: "blogPublish",
        ops: "新增"
      },
      confirmMsg: {
        title: "删除博客",
        msg: "您正在准备删除一篇博客，确定吗？"
      }
    };
  },
  activated() {
    this.getBlogList();
  },
  methods: {
    /**
     * 查询博客列表
     */
    getBlogList() {
      findBlogList(this.blogManageQueryVo).then(res => {
        this.blogList = res.data.data.list.map(item => {
          let id = item.id;
          let title = item.title;
          let type = item.type ? item.type.name : "无";
          let recommend = item.recommend ? "是" : "否";
          let top = item.top ? "是" : "否";
          let published = item.published? "发布" : "草稿"
          let updateTime = formatDate(
            new Date(item.updateTime),
            "yyyy-MM-dd hh:mm:ss"
          );
          return {
            id,
            title,
            type,
            recommend,
            top,
            published,
            updateTime
          };
        });
        console.log(res.data.data)
        this.isFirstPage = res.data.data.isFirstPage;
        this.isLastPage = res.data.data.isLastPage;
        this.prePage = res.data.data.prePage;
        this.nextPage = res.data.data.nextPage;
        this.blogManageQueryVo["currentPage"] = res.data.data.pageNum;
      });
    },
    /**
     * 翻页
     */
    toLastPage() {
      this.blogManageQueryVo.currentPage = this.prePage;
      this.getBlogList(this.blogManageQueryVo);
    },
    toNextPage() {
      this.$set(this.blogManageQueryVo, "currentPage", this.nextPage);
      this.getBlogList(this.blogManageQueryVo);
    },
    /**
     * 编辑与删除
     */
    edit(id) {
      this.$router.push({
        path: "/profile/blogEdit/" + id
      });
    },
    remove(id) {
      const _this = this;
      $(this.$refs.confirm.$el)
        .modal({
          onApprove: function() {
            deleteBlog(id).then(res => {
              if (res.data.flag) {
                _this.$toast.show("删除成功")
                _this.blogManageQueryVo.currentPage = _this.prePage + 1;
                _this.blogManageQueryVo.currentPage = _this.pageSize;
                _this.getBlogList(_this.blogManageQueryVo);
              } else {
                _this.$toast.show("删除失败" + res.data.errorMsg, false)
              }
            });
          }
        })
        .modal("show");
    },
    /**
     * 监听搜索事件
     */
    search(queryVo) {
      this.blogManageQueryVo.title = queryVo.title;
      this.blogManageQueryVo.typeId = queryVo.typeId;
      this.blogManageQueryVo.recommend = queryVo.recommend;
      this.getBlogList();
    },
  },
  components: {
    "manage-header": () => import("./unit/ManageHeader"),
    "manage-table": () => import("./unit/ManageTable"),
    "confirm-modal": () => import("components/modal/ConfirmModal")
  }
};
</script>

<style>
</style>