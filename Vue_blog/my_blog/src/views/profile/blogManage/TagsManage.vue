<template>
  <div id="main" class="m-padding-tb-max m-container-small">
    <div class="ui container">
      <manage-table
        :tableHeader="tableHeader"
        :tableList="tagList"
        :toOps="toOps"
        :isFirstPage="isFirstPage"
        :isLastPage="isLastPage"
        @toLastPage="toLastPage"
        @toNextPage="toNextPage"
        :currentPage="currentPage"
        :pageSize="pageSize"
        @edit="edit"
        @remove="remove"
      ></manage-table>
    </div>
    <confirm-modal :confirmMsg="confirmMsg" ref="confirm"></confirm-modal>
  </div>
</template>

<script>
import { findTags, deleteTag } from "network/profileAjax";

export default {
  name: "TagsManage",
  data() {
    return {
      tableHeader: ["", "名称", "博客数量", "操作"],
      tagList: null,
      currentPage: 1,
      pageSize: 5,
      toOps: {
        to: "tagPublish",
        ops: "新增"
      },
      isFirstPage: true,
      isLastPage: false,
      prePage: 0,
      nextPage: 0,
      confirmMsg: {
        title: "删除标签",
        msg: "您正在准备删除一个标签，确定吗？"
      }
    };
  },
  components: {
    "manage-table": () => import("./unit/ManageTable"),
    "confirm-modal": () => import("components/modal/ConfirmModal")
  },
  methods: {
    /**
     * ajax请求
     */
    getTags(currentPage, pageSize) {
      findTags(currentPage, pageSize).then(res => {
        if (res.data.flag) {
          res.data.data.list.map(item => delete item.blogList);
          this.tagList = res.data.data.list;
          this.isFirstPage = res.data.data.isFirstPage;
          this.isLastPage = res.data.data.isLastPage;
          this.prePage = res.data.data.prePage;
          this.nextPage = res.data.data.nextPage;
          this.currentPage = res.data.data.pageNum;
        }
      });
    },
    /**
     * 翻页
     */
    toLastPage() {
      this.getTags(this.prePage, this.pageSize);
    },
    toNextPage() {
      this.getTags(this.nextPage, this.pageSize);
    },
    /**
     * 编辑与删除
     */
    edit(id) {
      this.$router.push({
        path: "/profile/tagEdit/" + id
      });
    },
    remove(id) {
      const _this = this;
      $(this.$refs.confirm.$el)
        .modal({
          onApprove: function() {
            deleteTag(id).then(res => {
              if (res.data.flag) {
                _this.$toast.show("删除成功");
                _this.getTags(_this.prePage + 1, _this.pageSize);
              } else {
                _this.$toast.show("删除失败" + res.data.errorMsg, false);
              }
            });
          }
        })
        .modal("show");
    }
  },
  activated() {
    this.getTags(this.currentPage, this.pageSize);
  }
};
</script>

<style scoped>
</style>