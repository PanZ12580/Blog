<template>
  <div id="app">
    <nav-bar ref="navBar"></nav-bar>
    <div class="ui active inverted dimmer" v-show="isLoading">
      <div class="ui large text loader">加载中</div>
    </div>
    <keep-alive>
      <router-view :key="$router.fullPath"></router-view>
    </keep-alive>
    <my-footer></my-footer>
  </div>
</template>

<script>
import NavBar from "components/navbar/NavBar";

export default {
  name: "App",
  components: {
    NavBar,
    "my-footer": () => import("components/footer/MyFooter"),
  },
  computed: {
    isLoading() {
      return this.$store.state.loading
    }
  }
};
</script>

<style scoped>
@import "~assets/css/mine.css";
@import "~assets/css/animate.min.css";
.dimmer {
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  position: fixed;
}
</style>
