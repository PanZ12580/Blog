<template>
  <div id="home">
    <masthead ref="masthead"></masthead>
    <main-content ref="header">
      <template #blog_list>
        <home-list
          :mainList="mainList"
          :typesList="typesList"
          :tagsList="tagsList"
          :recommendList="recommendList"
          :totalBlogs="totalBlogs"
          :hasPreviousPage="hasPreviousPage"
          :hasNextPage="hasNextPage"
          @prePage="toPrePage"
          @nextPage="toNextPage"
        ></home-list>
      </template>
    </main-content>
    <tool-bar ref="toolBar"></tool-bar>
  </div>
</template>

<script>
import Masthead from "./masthead/Masthead";
import MainContent from "components/blogList/MainContent";

import { toolbarControl } from "common/mixin";
import {
  findBlogList,
  findTypeList,
  findTagList,
  findRecommendList
} from "network/homeAjax";

export default {
  name: "Home",
  components: {
    Masthead,
    MainContent,
    "home-list": () => import("components/blogList/home/HomeList")
  },
  data() {
    return {
      pageSize: 10,
      hasPreviousPage: false,
      hasNextPage: false,
      prePage: 0,
      nextPage: 1,
      totalBlogs: Number,
      mainList: [],
      typesList: [],
      tagsList: [],
      recommendList: []
    };
  },
  activated() {
    this.navControl();
    this.getBlogList();
    this.getTypeList();
    this.getTagList();
    this.getRecommendList();
  },
  methods: {
    /**
     * 控制导航栏显示于隐藏
     */
    navControl() {
      const masthead = this.$refs.masthead.$el;
      $(masthead).visibility({
        once: false,
        onBottomPassed: function() {
          $(".fixed.menu").transition("fade in");
        },
        onBottomPassedReverse: function() {
          $(".fixed.menu").transition("fade out");
        }
      });
    },
    /**
     * 翻页
     */
    toPrePage() {
      this.getBlogList(this.prePage)
    },
    toNextPage() {
      this.getBlogList(this.nextPage)
    },
    /**
     * 获取博客列表
     */
    getBlogList(currentPage = 1) {
      findBlogList(currentPage, this.pageSize).then(res => {
        this.mainList = res.data.data.list;
        this.prePage = res.data.data.prePage;
        this.nextPage = res.data.data.nextPage;
        this.totalBlogs = res.data.data.total;
        this.hasPreviousPage = res.data.data.hasPreviousPage;
        this.hasNextPage = res.data.data.hasNextPage;
      });
    },
    /**
     * 获取分类列表
     */
    getTypeList() {
      findTypeList().then(res => {
        this.typesList = res.data.data;
      });
    },
    /**
     * 获取标签列表
     */
    getTagList() {
      findTagList().then(res => {
        this.tagsList = res.data.data;
      });
    },
    /**
     * 获取推荐博客列表
     */
    getRecommendList() {
      findRecommendList().then(res => {
        this.recommendList = res.data.data;
      });
    }
  },
  mixins: [toolbarControl]
};
</script>

<style scoped>
</style>