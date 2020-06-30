<template>
  <div id="main" class="m-padding-tb-max m-margin-top-small">
    <div class="ui container stackable">
      <uv-statistic></uv-statistic>
      <div class="ui segment basic center right aligned">
        <div class="center aligned stackable container ui side-container">
          <div id="flip_left" class="ui basic button teal circular">
            <img src="http://cdn.panzvor.com/double-left.png" alt class="circular-img" />
          </div>
          <div id="flip_right" class="ui teal basic button circular">
            <img src="http://cdn.panzvor.com/double-right.png" alt class="circular-img" />
          </div>
          <div class="segment basic center aligned ui shape cube m-cube">
            <div class="ui sides">
              <div class="active side m-shape-side container stackable ui">
                <img class="ui image" alt src="http://cdn.panzvor.com/kieed-miku.gif" />
              </div>
              <side aboutTitle="关于本站" :aboutContent="aboutBlog">
                <template #extra>
                  <appreciate></appreciate>
                </template>
              </side>
              <side aboutTitle="关于作者" :aboutContent="aboutAuthor">
                <template #extra>
                  <contact></contact>
                </template>
              </side>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { scrollEventListener } from "common/mixin";

export default {
  name: "About",
  components: {
    side: () => import("./unit/Side"),
    appreciate: () => import("./unit/Appreciate"),
    contact: () => import("./unit/Contact"),
    "uv-statistic": () => import("components/uvStatistic/UVStatistic")
  },
  data() {
    return {
      aboutAuthor: `<h4 style='margin-bottom: 1.5em;'>2021届学生一名，信息管理与信息系统专业，喜欢Code、Draw、Run</h4>
      练习时长尚不足两年半，
      自知无论是码龄还是画龄和许多同龄或更年轻的大佬比起来都是微不足道，
      将自己丢出去不过是沧海一粟，
      漫长的学习生涯中一直没有为自己找到专精的技能方向，
      立志将代码、绘画这两项技能作为一生的坚持，越学越觉得自己菜，越菜越需要学。`,
      aboutBlog: `
      <strong>前端开发框架：</strong>Vue.js 2.5<br>
      <strong>前端UI框架：</strong>Semantic UI 2.4<br>
      <strong>后端开发框架：</strong>SpringBoot 2.3.0<br>
      <strong>后端持久层框架：</strong>MyBatis 3.5.4<br>
      <strong>数据库：</strong>MySQL 8.0.19、Redis 3.2.1<br>
      一开始想做的时候没想着用Vue的，后来做着做着还是决定用Vue重构一下，于是花了两周多的时间上B站看了4000多分钟的教程视频，
      边看边敲，收获很多。做完这个网站之后，接下来就要去重温基础了，然后进入SpringCloud的学习，
      其实还想给网站加一些功能的，代码结构有些地方也想优化下，后续有时间再来吧，争取将站点一直维护下去。
      `
    };
  },
  mounted() {
    $("#flip_right").click(function() {
      $(".ui.cube.shape")
        .shape()
        .shape("flip right");
    });
    $("#flip_left").click(function() {
      $(".ui.cube.shape")
        .shape()
        .shape("flip left");
    });
  },
  mixins: [scrollEventListener]
};
</script>

<style scoped>
@media only screen and (max-width: 800px) {
  .ui.basic.teal.button {
    border: none !important;
    box-shadow: none !important;
    -webkit-box-shadow: none !important;
  }
  .circular-img {
    opacity: 0.4;
  }
  #flip_left:active {
    background-color: rgb(255, 193, 255) !important;
    box-shadow: none !important;
    -webkit-box-shadow: none !important;
  }
  #flip_right:active {
    background-color: rgb(255, 193, 255) !important;
    box-shadow: none !important;
    -webkit-box-shadow: none !important;
  }
}
.side-container {
  position: relative;
}
#flip_left {
  position: absolute;
  left: 0;
  top: 50%;
  z-index: 9;
}
#flip_right {
  position: absolute;
  right: 0;
  top: 50%;
  z-index: 9;
}
#flip_left:active {
  background-color: rgb(255, 255, 255) !important;
  box-shadow: none !important;
  -webkit-box-shadow: none !important;
}
#flip_right:active {
  background-color: rgb(255, 255, 255) !important;
  box-shadow: none !important;
  -webkit-box-shadow: none !important;
}
.circular {
  border-radius: 50% !important;
  width: 4em;
  height: 4em;
  position: relative;
}
.circular-img {
  border-radius: 50% !important;
  width: 2.5em;
  height: 2.5em;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>