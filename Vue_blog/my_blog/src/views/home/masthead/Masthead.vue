<template>
  <!--    巨幕-->
  <div id="masthead" class="ui vertical masthead center aligned segment">
    <div class="m-masthead-cover"></div>
    <div class="ui container">
      <div class="animate__animated animate__zoomIn">
        <img
          id="headImg"
          class="ui image avatar"
          style="width: 150px; height: 150px;"
          alt
          src="~assets/images/64304136_p0.jpg"
        />
        <h1 class="ui header m-white">Hello World</h1>
        <h2 class="m-white">Do whatever you want when you want to.</h2>
        <div @click="toMain" class="ui primary button m-margin-top-medium">
          进入首页
          <i class="angle double down icon m-padding-lr-small"></i>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import eventHandle from "common/event";

export default {
  name: "Masthead",
  data() {
    return {
      target: 150 / 1519,
      originScreenHeight: document.documentElement.clientHeight
    };
  },
  methods: {
    /**
     * 滚动事件
     */
    toMain() {
      document
        .getElementById("main")
        .scrollIntoView({
          behavior: "smooth",
          block: "start",
          inline: "nearest"
        });
    },
    /**
     * 头像大小自适应
     */
    resizeImg() {
      let screenWidth = document.documentElement.clientWidth;
      let screenHeight = document.documentElement.clientHeight;

      $("#headImg")
        .css("width", screenWidth * this.target)
        .css("height", screenWidth * this.target);
    },
    resizeHeadImg() {
      let screenWidth = document.documentElement.clientWidth;
      let screenHeight = document.documentElement.clientHeight;
      let screenSize = Math.sqrt(
        screenWidth * screenWidth + screenHeight * screenHeight
      );
      $("#headImg")
        .css("width", screenWidth * this.target)
        .css("height", screenWidth * this.target);
      eventHandle.addEvent(window, "resize", this.resizeImg);
    }
  },
  activated() {
    this.resizeHeadImg();
  },
  deactivated() {
    eventHandle.removeEvent(window, "resize", this.resizeImg);
  }
};
</script>

<style>
</style>