<template>
  <div>
    <div id="main" class="m-padding-tb-max m-container-small">
      <div id="main_container" class="ui container">
        <common-header ref="header" :blogCount="count"></common-header>
        <categories-types :typesList="typesList"></categories-types>
        <main-list :attachClass="attachClass">
          <template #default>
            <main-list-item v-for="(item,index) in mainList" :key="index" :listItem="item"></main-list-item>
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
      count: 14,
      attachClass: ["top", "teal"],
      typesList: [
        { name: "Java相关", count: 24 },
        { name: "透视与结构", count: 24 },
        { name: "光影与色彩", count: 24 },
        { name: "构成分析", count: 24 },
        { name: "MySQL", count: 24 },
        { name: "Redis", count: 24 }
      ],
      mainList: [
        {
          header: "Java从入门到入土",
          text:
            "Unsplash IT 网站是一个基于免费高清壁纸分享网UnSplash网站的一个图片自定义链接参数设定的站点,可以帮助网页设计师使用UnSplash网站上的高清图片来做网页占位图放置在...",
          author: "PanzVor",
          date: "2020-5-14",
          views: 1999,
          type: "Java相关",
          imgSrc: "https://picsum.photos/800/500",
          headImg: "https://picsum.photos/100/100"
        },
        {
          header: "Java从入门到入土",
          text:
            "Unsplash IT 网站是一个基于免费高清壁纸分享网UnSplash网站的一个图片自定义链接参数设定的站点,可以帮助网页设计师使用UnSplash网站上的高清图片来做网页占位图放置在...",
          author: "PanzVor",
          date: "2020-5-14",
          views: 1999,
          type: "Java相关",
          imgSrc: "https://picsum.photos/800/500",
          headImg: "https://picsum.photos/100/100"
        },
        {
          header: "Java从入门到入土",
          text:
            "Unsplash IT 网站是一个基于免费高清壁纸分享网UnSplash网站的一个图片自定义链接参数设定的站点,可以帮助网页设计师使用UnSplash网站上的高清图片来做网页占位图放置在...",
          author: "PanzVor",
          date: "2020-5-14",
          views: 1999,
          type: "Java相关",
          imgSrc: "https://picsum.photos/800/500",
          headImg: "https://picsum.photos/100/100"
        },
        {
          header: "Java从入门到入土",
          text:
            "Unsplash IT 网站是一个基于免费高清壁纸分享网UnSplash网站的一个图片自定义链接参数设定的站点,可以帮助网页设计师使用UnSplash网站上的高清图片来做网页占位图放置在...",
          author: "PanzVor",
          date: "2020-5-14",
          views: 1999,
          type: "Java相关",
          imgSrc: "https://picsum.photos/800/500",
          headImg: "https://picsum.photos/100/100"
        }
      ]
    };
  },
  mixins: [scrollEventListener, toolbarControl],
  deactivated() {
    $(".fixed.menu").transition("fade out");
  }
};
</script>

<style>
</style>