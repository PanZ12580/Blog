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
    <!-- <div>
      <aplayer v-if="isShow" :audio="audio" :lrcType="3" fixed mini ref="aplayer" />
    </div>-->
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
      audio: [],
      musicList: [],
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
  watch: {
    audio() {
      if (this.audio.length > 1) {
        for (let i = 0; i < this.musicList.length; i++) {
          this.$set(this.audio[i], "url", this.musicList[i]);
        }
        let aplayer = this.$refs.aplayer;
        console.log(aplayer);
      }
    }
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
    },
    async initSong() {
      findPlayList()
        .then(res => {
          this.musicList = res.data.playlist.trackIds.map(
            item =>
              `https://music.163.com/song/media/outer/url?id=${item.id}.mp3`
          );
          return res.data.playlist.trackIds;
        })
        .then(res => {
          let ids = res.map(item => item.id);
          ids = ids.join(",");
          findSongDetail(ids).then(res => {
            this.audio = res.data.songs.map(item => {
              let songId = item.id;
              let name = item.name;
              let artist = item.ar[0].name;
              let cover = item.al.picUrl;
              return {
                name,
                artist,
                cover,
                lrc: "https://autumnfish.cn/lyric?id=" + songId
              };
            });
          });
          this.isShow = true;
        });
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
