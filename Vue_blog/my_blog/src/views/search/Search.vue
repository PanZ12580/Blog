<template>
  <div>
    <div id="main" class="m-padding-tb-max m-container-small">
      <div id="main_container" class="ui container">
        <common-header ref="header" :title="title" :count="totalBlogs" :unit="unit"></common-header>
        <main-list :attachClass="attachClass">
          <template #default>
            <main-list-item v-for="(item, index) in mainList" :key="index" :listItem="item"></main-list-item>
          </template>
        </main-list>
        <list-footer
          :hasPreviousPage="hasPreviousPage"
          :hasNextPage="hasNextPage"
          @prePage="toPrePage"
          @nextPage="toNextPage"
        ></list-footer>
      </div>
      <tool-bar ref="toolBar"></tool-bar>
    </div>
  </div>
</template>

<script>
import CommonHeader from "components/commonHeader/CommonHeader";

import { scrollEventListener, toolbarControl } from "common/mixin";
import { findSearchBlogList } from "network/homeAjax";
import { SET_SEARCH } from "store/mutation_types";

export default {
  name: "Search",
  components: {
    CommonHeader,
    "main-list": () => import("components/blogList/MainList"),
    "main-list-item": () => import("components/blogList/MainListItem"),
    "list-footer": () => import("components/blogList/ListFooter")
  },
  data() {
    return {
      attachClass: ["top", "teal"],
      title: "搜索结果",
      mainList: [],
      pageSize: 10,
      hasPreviousPage: false,
      hasNextPage: false,
      prePage: 0,
      nextPage: 1,
      totalBlogs: 0,
      unit: "篇"
    };
  },
  activated() {
    this.getSearchBlogList();
    this.$bus.$on("toSearch", this.getSearchBlogList);
  },
  mixins: [scrollEventListener, toolbarControl],
  deactivated() {
    if (this.$route.fullPath.indexOf("detail") === -1) {
      window.sessionStorage.removeItem("search");
      this.$store.commit(SET_SEARCH, "");
      this.$bus.$off("toSearch", this.getSearchBlogList);
    }
  },
  methods: {
    /**
     * 请求博客列表
     */
    getSearchBlogList(currentPage = 1) {
      let search = window.sessionStorage.getItem("search");
      findSearchBlogList(currentPage, this.pageSize, search).then(res => {
        if (res.data.data.list) {
          this.mainList = res.data.data.list;
        }
        this.prePage = res.data.data.prePage;
        this.nextPage = res.data.data.nextPage;
        this.totalBlogs = res.data.data.total;
        this.hasPreviousPage = res.data.data.hasPreviousPage;
        this.hasNextPage = res.data.data.hasNextPage;
      });
    },
    /**
     * 翻页
     */
    toPrePage() {
      this.getSearchBlogList(this.prePage);
    },
    toNextPage() {
      this.getSearchBlogList(this.nextPage);
    }
  }
};
</script>

<style>
</style>