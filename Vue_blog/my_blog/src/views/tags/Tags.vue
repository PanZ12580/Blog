<template>
  <div class="m-padding-tb-max m-container-small">
    <div id="main_container" class="ui container">
      <common-header ref="header" :title="title" :count="totalTags" :unit="unit"></common-header>
      <tag-list :tagList="tagList" @itemClick="itemClick" :selectedId="tagId"></tag-list>
      <main-list :attachClass="attachClass">
        <template #default>
          <main-list-item
            :selectedId="tagId"
            v-for="(item,index) in mainList"
            :key="index"
            :listItem="item"
          ></main-list-item>
        </template>
      </main-list>
      <list-footer
        :hasPreviousPage="hasPreviousPage"
        :hasNextPage="hasNextPage"
        @prePage="toPrePage"
        @nextPage="toNextPage"
      ></list-footer>
      <tool-bar ref="toolBar"></tool-bar>
    </div>
  </div>
</template>

<script>
import CommonHeader from "components/commonHeader/CommonHeader";

import { scrollEventListener, toolbarControl } from "common/mixin";
import { findTagList, findBlogByTagId } from "network/tagAjax";

export default {
  name: "Tags",
  data() {
    return {
      title: "标签",
      attachClass: ["top", "teal"],
      totalTags: 0,
      unit: "个",
      tagList: [],
      tagId: 0,
      mainList: [],
      pageSize: 10,
      hasPreviousPage: false,
      hasNextPage: false,
      prePage: 0,
      nextPage: 1
    };
  },
  components: {
    CommonHeader,
    "tag-list": () => import("./unit/TagList"),
    "main-list": () => import("components/blogList/MainList"),
    "main-list-item": () => import("components/blogList/MainListItem"),
    "list-footer": () => import("components/blogList/ListFooter")
  },
  watch: {
    $route() {
      if (this.$route.params.tagId) {
        this.tagId = Number(this.$route.params.tagId);
      }
      this.getTagList();
      this.getBlogByTagId();
    }
  },
  mixins: [scrollEventListener, toolbarControl],
  activated() {
    if (this.$route.params.tagId) {
      this.tagId = Number(this.$route.params.tagId);
    }
    this.getTagList();
    this.getBlogByTagId();
  },
  deactivated() {
    Object.assign(this.$data, this.$options.data());
  },
  methods: {
    /**
     * 获取标签列表
     */
    getTagList() {
      findTagList().then(res => {
        this.tagList = res.data.data.list;
        this.totalTags = res.data.data.total;
      });
    },
    /**
     * 根据标签id获取博客列表
     */
    getBlogByTagId(currentPage = 1) {
      findBlogByTagId(currentPage, this.pageSize, this.tagId).then(res => {
        this.mainList = res.data.data.list;
        this.prePage = res.data.data.prePage;
        this.nextPage = res.data.data.nextPage;
        this.hasPreviousPage = res.data.data.hasPreviousPage;
        this.hasNextPage = res.data.data.hasNextPage;
      });
    },
    /**
     * 翻页
     */
    toPrePage() {
      this.getBlogByTagId(this.prePage);
    },
    toNextPage() {
      this.getBlogByTagId(this.nextPage);
    },
    /**
     * 标签点击
     */
    itemClick(tagId) {
      this.$router.push({
        name: "TagsWithId",
        params: {
          tagId
        }
      });
    }
  }
};
</script>

<style>
</style>