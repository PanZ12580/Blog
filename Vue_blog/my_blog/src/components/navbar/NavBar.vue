<template>
  <div>
    <div
      class="ui secondary doubling transition top hidden fixed menu m-nav-bg m-shadow-down m-padding-tb-mini"
    >
      <h2 class="ui teal four column header item">PanzVor</h2>
      <div class="ui item m-item m-mobile-hide">
        <nav-bar-item v-for="(item, index) in titles" :key="index" :to="item.to">
            <template #default>
              <i class="icon teal" :class="item.icon"></i>{{item.title}}
            </template>
          </nav-bar-item>
      </div>
      <div class="right item m-mobile-hide">
        <div class="ui large transparent icon input">
          <input type="text" placeholder="Search..." />
          <i class="search teal icon link"></i>
        </div>
      </div>
      <!-- 手机响应 -->
      <div class="ui dropdown icon item right m-mobile-show">
        <i class="sidebar teal icon m-icon-center"></i>
        <div class="menu">
          <div class="ui icon search input">
            <i class="search teal icon link"></i>
            <input type="text" placeholder="Search..." />
          </div>
          <div class="divider"></div>
          <nav-bar-item v-for="(item, index) in titles" :key="index" :to="item.to">
            <template #default>
              <i class="icon teal" :class="item.icon"></i>{{item.title}}
            </template>
          </nav-bar-item>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBarItem from "./mobileHide/NavBarItem";

export default {
  name: "NavBar",
  components: {
    NavBarItem
  },
  data() {
    return {
      titles: [
        {to: '/home', title: '首页', icon: "home"},
        {to: '/categories', title: '分类', icon: "idea"},
        {to: '/tags', title: '标签', icon: "tags"},
        {to: '/archives', title: '归档', icon: "clone"},
        {to: '/message', title: '留言', icon: "envelope"},
        {to: '/about', title: '关于我', icon: "info"},
      ]
    }
  },
  mounted() {
    $(".ui.dropdown").dropdown({
      on: "click"
    });
    $(".alter_dropdown").popup({
      on: "hover",
      popup: $(".login_out"),
      position: "bottom right",
      hoverable: true
    });
  }
};
</script>

<style>
</style>