<template>
  <!--底部footer-->
  <footer class="ui m-shadow-up vertical footer segment m-padding-tb-max m-bg">
    <div class="ui container center aligned">
      <div class="ui stackable divided center aligned equal height stackable grid">
        <div class="three wide column">
          <div class="ui link list">
            <div class="item">
              <img
                src="http://cdn.panzvor.com/wechat.png"
                alt
                class="ui rounded image"
                style="width: 140px"
              />
            </div>
          </div>
        </div>
        <div class="three wide column m-text-lined">
          <h4 class="ui header m-text-thin m-text-spaced">最新博客</h4>
          <div class="ui link list">
            <router-link
              class="item m-text-lined m-lasted"
              v-for="item in $store.state.lastBlogList"
              :key="item.id"
              v-text="item.title"
              :to="'/detail/' + item.id"
              target="_blank"
              tag="a"
              :title="item.title"
            ></router-link>
          </div>
        </div>
        <div class="three wide column">
          <h4 class="ui header m-text-thin m-text-spaced">联系我</h4>
          <div class="ui link list">
            <a
              :href="'mailto:' + $store.state.admin.email"
              class="item m-text-lined"
              v-text="'Email: ' + $store.state.admin.email"
            ></a>
            <a
              :href="'http://wpa.qq.com/msgrd?v=3&amp;uin=' + $store.state.admin.qq + '&amp;site=qq&amp;menu=yes'"
              target="_blank"
              class="item m-text-lined"
              v-text="'QQ: ' + $store.state.admin.qq"
            ></a>
          </div>
        </div>
        <div class="seven wide column">
          <h4 class="ui header m-text-thin m-text-spaced">博客介绍</h4>
          <p class="m-text-thin m-text-spaced m-opacity-mini m-text-lined" v-text="introduce"></p>
        </div>
      </div>
      <div class="ui section divider"></div>
      <p class="m-text-thin m-text-spaced m-opacity-mini" v-text="message"></p>
    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer",
  data() {
    return {
      introduce:
        "基于Vue + SpringBoot开发的个人博客，UI框架使用SemanticUI，博客将一直维护下去，欢迎大佬们指出bug或改进，欢迎交流！欢迎互链！",
      message: `粤ICP备20056077号-1 | © 2020-2021 panzvor.com
      | Email：344096911@qq.com`
    };
  }
};
</script>

<style scoped>
.m-lasted {
  width: 100% !important;
  line-height: 2em !important;
  overflow: hidden !important;
  white-space: nowrap !important;
  text-overflow: ellipsis !important;
}
</style>