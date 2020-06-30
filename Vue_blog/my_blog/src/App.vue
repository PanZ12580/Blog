<template>
  <div id="app">
    <div id="app-scroll"></div>
    <nav-bar ref="navBar"></nav-bar>
    <div class="ui active inverted dimmer" v-show="isLoading">
      <div class="ui large text loader">加载中</div>
    </div>
    <keep-alive :max="5" :exclude="['Detail']">
      <router-view :key="$router.fullPath"></router-view>
    </keep-alive>
    <meting-js fixed="true" server="netease" type="playlist" id="138900787"></meting-js>
    <my-footer></my-footer>
  </div>
</template>

<script>
import NavBar from "components/navbar/NavBar";

import { findLyric, findPlayList, findSongDetail } from "network/musicAjax";
import eventHandler from "common/event";
import { SET_MUSIC_STATUS } from "store/mutation_types";

export default {
  name: "App",
  data() {
    return {
      ap: null
    };
  },
  components: {
    NavBar,
    "my-footer": () => import("components/footer/MyFooter")
  },
  computed: {
    isLoading() {
      return this.$store.state.loading;
    }
  },
  mounted() {
    this.$store.dispatch("getAdmin");
    this.$store.dispatch("getLastBlogList");
    let _this = this;
    Object.defineProperty(document.querySelector("meting-js"), "aplayer", {
      set: function(aplayer) {
        _this.ap = aplayer;
        _this.ready();
      }
    });
  },
  beforeDestroy() {
    this.ap.destroy();
  },
  methods: {
    /**
     * 播放事件
     */
    ready() {
      const _this = this;
      this.ap.on("play", () => {
        this.$store.commit(SET_MUSIC_STATUS, true);
      });
      this.ap.on("pause", () => {
        this.$store.commit(SET_MUSIC_STATUS, false);
      });
    }
  }
};
</script>

<style scoped>
@import "~assets/css/mine.css";
.dimmer {
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  position: fixed;
}
</style>
