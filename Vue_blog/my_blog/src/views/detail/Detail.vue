<template>
  <div
    id="main"
    class="m-padding-tb-max m-container-small animate__animated animate__fadeIn line-numbers"
  >
    <div id="main_container" class="ui container">
      <content-header ref="header"></content-header>
      <content-img></content-img>
      <main-content></main-content>
      <blog-message></blog-message>
      <comment ref="comment"></comment>
    </div>
    <tool-bar ref="toolBar">
      <template #default>
        <button id="toc_btn" type="buttton" class="ui button teal m-bg-teal">目录</button>
        <a @click="toMessage" class="ui teal button m-bg-teal">留言</a>
        <button class="ui icon button wechat_qr teal m-bg-teal">
          <i class="ui icon weixin"></i>
        </button>
      </template>
    </tool-bar>
    <!--    目录-->
    <div
      class="ui js-toc flowing toc_container popup transition m-padding-lr-tiny hidden"
      style="width: 250px !important;max-width: 500px !important;"
    ></div>
    <!--    微信二维码-->
    <div
      id="qrcode"
      class="ui wechat flowing popup top left transition hidden"
      style="width: 120px;"
    ></div>
  </div>
</template>

<script>
import ContentHeader from "./mainContent/ContentHeader";

import Prism from "assets/lib/prism/prism";
import QRCode from "qrcodejs2";
import tocbot from "assets/js/tocbot.min";
import { scrollEventListener, toolbarControl } from "common/mixin";

export default {
  name: "Detail",
  components: {
    ContentHeader,
    "content-img": () => import("./mainContent/ContentImg"),
    "main-content": () => import("./mainContent/MainContent"),
    "blog-message": () => import("./mainContent/BlogMessage"),
    "comment": () => import("components/comment/Comment")
  },
  mounted() {
    this.$nextTick(() => {
      Prism.highlightAll();
      tocbot.init({
        // Where to render the table of contents.
        tocSelector: ".js-toc",
        // Where to grab the headings to build the table of contents.
        contentSelector: ".js-toc-content",
        // Which headings to grab inside of the contentSelector element.
        headingSelector: "h1, h2, h3"
        // For headings inside relative or absolute positioned containers within content.
        //   hasInnerContainers: true
      });
      //    目录
      $("#toc_btn").popup({
        popup: $(".toc_container"),
        on: "click",
        position: "left center"
      });
      //    微信二维码
      $(".wechat_qr").popup({
        popup: $(".wechat.popup"),
        position: "left center",
        hoverable: true
      });
      //    生成二维码
      var qrcode = new QRCode("qrcode", {
        text: window.location,
        width: 120,
        height: 120,
        colorDark: "#000000",
        colorLight: "#ffffff"
      });
    });
  },
  methods: {
    toMessage() {
      this.$refs.comment.$el.scrollIntoView({
        behavior: "smooth",
        block: "start",
        inline: "nearest"
      });
    }
  },
  mixins: [scrollEventListener, toolbarControl],
  deactivated() {
    $(".fixed.menu").transition("fade out");
  }
};
</script>

<style scoped>
@import "~assets/css/typo.css";
@import "~assets/css/tocbot.css";
@import "~assets/lib/prism/prism.css";
#main {
  position: -webkit-sticky;
  position: sticky;
  z-index: 1;
}
</style>