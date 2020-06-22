<template>
  <div>
    <div id="main" class="m-padding-tb-max m-container-small">
      <div id="main_container" class="ui container">
        <common-header ref="header" :title="title" :blogCount="totalBlogs"></common-header>
        <main-list :attachClass="attachClass">
          <template #default>
            <main-list-item v-for="(item, index) in mainList" :key="index" :listItem="item"></main-list-item>
          </template>
        </main-list>
        <list-footer></list-footer>
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
      hasPreviousPage: Boolean,
      hasNextPage: Boolean,
      prePage: 0,
      nextPage: 1,
      totalBlogs: 0
    };
  },
  activated() {
    this.getSearchBlogList();
    this.$bus.$on("toSearch", this.getSearchBlogList);
  },
  mixins: [scrollEventListener, toolbarControl],
  deactivated() {
    $(".fixed.menu").transition("fade out");
    window.sessionStorage.removeItem("search");
    this.$store.commit(SET_SEARCH, "");
    this.$bus.$off("toSearch", this.getSearchBlogList);
  },
  methods: {
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
    }
  }
};
</script>

<style>
</style>