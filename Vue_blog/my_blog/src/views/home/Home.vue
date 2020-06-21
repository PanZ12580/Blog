<template>
  <div id="home">
    <masthead ref="masthead"></masthead>
    <main-content ref="header">
      <template #blog_list>
        <home-list></home-list>
      </template>
    </main-content>
    <tool-bar ref="toolBar"></tool-bar>
  </div>
</template>

<script>
import Masthead from "./masthead/Masthead";
import MainContent from "components/blogList/MainContent";

import { toolbarControl } from "common/mixin";

export default {
  name: "Home",
  components: {
    Masthead,
    MainContent,
    "home-list": () => import("components/blogList/home/HomeList")
  },
  data() {
    return {
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
      ],
      typesList: [
        { name: "Java相关", count: 15 },
        { name: "透视与结构", count: 4 },
        { name: "光影与色彩", count: 3 },
        { name: "构成分析", count: 21 },
        { name: "MySQL", count: 9 },
        { name: "Redis", count: 8 }
      ],
      tagsList: [
        { name: "SpringMVC", count: 22 },
        { name: "Spring", count: 4 },
        { name: "Vue", count: 5 },
        { name: "Java", count: 12 },
        { name: "数据结构与算法", count: 3 },
        { name: "Dubbo", count: 11 },
        { name: "ES6", count: 6 }
      ],
      recommendList: ["用户故事", "装机必备", "算命", "补证办证"]
    };
  },
  provide() {
    return {
      mainList: this.mainList,
      typesList: this.typesList,
      tagsList: this.tagsList,
      recommendList: this.recommendList
    };
  },
  activated() {
    this.navControl();
  },
  methods: {
    /**
     * 控制导航栏显示于隐藏
     */
    navControl() {
      const masthead = this.$refs.masthead.$el
      $(masthead).visibility({
        once: false,
        onBottomPassed: function() {
          $(".fixed.menu").transition("fade in");
        },
        onBottomPassedReverse: function() {
          $(".fixed.menu").transition("fade out");
        }
      });
    }
  },
  mixins: [toolbarControl]
};
</script>

<style scoped>
</style>