<template>
  <div>
    <div id="main" class="m-padding-tb-max m-container-small">
      <div id="main_container" class="ui container">
        <common-header ref="header" :title="title" :count="totalTypes" :unit="unit"></common-header>
        <categories-types :selectedId="typeId" :typesList="typeList" @itemClick="itemClick"></categories-types>
        <main-list :attachClass="attachClass">
          <template #default>
            <main-list-item v-for="(item,index) in mainList" :key="index" :listItem="item"></main-list-item>
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
import { findTypeList, findBlogByTypeId } from "network/typeAjax";

export default {
  name: "Categories",
  components: {
    CommonHeader,
    "categories-types": () => import("./unit/CategoriesTypes"),
    "main-list": () => import("components/blogList/MainList"),
    "main-list-item": () => import("components/blogList/MainListItem"),
    "list-footer": () => import("components/blogList/ListFooter")
  },
  data() {
    return {
      totalTypes: 14,
      title: "分类",
      attachClass: ["top", "teal"],
      typeList: [],
      typeId: 0,
      unit: "个",
      mainList: [],
      pageSize: 10,
      hasPreviousPage: false,
      hasNextPage: false,
      prePage: 0,
      nextPage: 1
    };
  },
  watch: {
    $route() {
      if (this.$route.params.typeId) {
        this.typeId = Number(this.$route.params.typeId);
      }
      this.getTypeList();
      this.getBlogByTypeId();
    }
  },
  activated() {
    if (this.$route.params.typeId) {
      this.typeId = Number(this.$route.params.typeId);
    }
    this.getTypeList();
    this.getBlogByTypeId();
  },
  methods: {
    /**
     * 获取分类列表
     */
    getTypeList() {
      findTypeList().then(res => {
        this.typeList = res.data.data.list;
        this.totalTypes = res.data.data.total;
      });
    },
    /**
     * 根据分类Id获取博客列表
     */
    getBlogByTypeId(currentPage = 1) {
      findBlogByTypeId(currentPage, this.pageSize, this.typeId).then(res => {
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
      this.getBlogByTypeId(this.prePage);
    },
    toNextPage() {
      this.getBlogByTypeId(this.nextPage);
    },
    /**
     * 标签点击
     */
    itemClick(typeId) {
      this.$router.push({
        name: "CategoriesWithId",
        params: {
          'typeId': typeId
        }
      })
    }
  },
  mixins: [scrollEventListener, toolbarControl],
  deactivated() {
    Object.assign(this.$data, this.$options.data());
  }
};
</script>

<style>
</style>