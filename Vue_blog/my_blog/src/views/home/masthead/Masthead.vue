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
          :class="{'music-start': $store.state.musicStart}"
          alt
          :src="this.$store.state.admin.avatar"
        />
        <h1 class="ui header m-white nickname" v-text="this.$store.state.admin.nickname"></h1>
        <h3 class="m-white tip" v-text="tip"></h3>
        <div>
          <a
            :href="'mailto:' + this.$store.state.admin.email"
            v-text="'Email: ' + this.$store.state.admin.email"
            class="email"
          ></a>
        </div>
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
      tip:
        "温馨提示：本网站所用的图片均来源于网络，其中网站装饰所用图片来自Krenz、Kieed、蘑菇Rella三位大大，如有不妥请联系博主，将立即进行处理",
      target: 150 / 1519,
      originScreenHeight: document.documentElement.clientHeight
    };
  },
  methods: {
    /**
     * 滚动事件
     */
    toMain() {
      document.getElementById("main").scrollIntoView({
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

<style scoped>
.tip {
  width: 60%;
  margin-left: auto;
  margin-right: auto;
  letter-spacing: 0.2em;
  background-color: rgba(214, 205, 205, 0.4);
  padding: 1em;
  border-radius: 0.4em;
}
.nickname {
  font-weight: 700 !important;
}
@media only screen and (max-width: 800px) {
  .tip {
    width: 21em;
    font-size: 1em;
    letter-spacing: 0.1em;
  }
}
.email {
  color: white;
}
#headImg {
  min-width: 70px;
  min-height: 70px;
}

@-webkit-keyframes spin {
  0% {
    -webkit-transform: rotate(0deg);
    border: 2px solid rgb(94, 191, 251);
    -webkit-box-shadow: 0 0 1em rgb(94, 191, 251); 
    padding: 0.4em;
  }
  25% {
    border: 3px solid rgb(226, 99, 99);
    -webkit-box-shadow: 0 0 1.5em rgb(226, 99, 99); 
    padding: 0.6em;
  }
  50% {
    border: 5px solid rgb(235, 29, 246);
    -webkit-box-shadow: 0 0 2em rgb(235, 29, 246); 
    padding: 0.8em;
  }
  75% {
    border: 3px solid rgb(231, 255, 52);
    -webkit-box-shadow: 0 0 1.5em rgb(231, 255, 52);
    padding: 0.6em;
  }
  100% {
    -webkit-transform: rotate(360deg);
    border: 2px solid rgb(117, 52, 246);
    -webkit-box-shadow: 0 0 1em rgb(117, 52, 246);
    padding: 0.4em;
  }
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
    border: 2px solid rgb(94, 191, 251);
    box-shadow: 0 0 1em rgb(94, 191, 251); 
    padding: 0.4em;
  }
  25% {
    border: 3px solid rgb(226, 99, 99);
    box-shadow: 0 0 1.5em rgb(226, 99, 99); 
    padding: 0.6em;
  }
  50% {
    border: 5px solid rgb(235, 29, 246);
    box-shadow: 0 0 2em rgb(235, 29, 246); 
    padding: 0.8em;
  }
  75% {
    border: 3px solid rgb(231, 255, 52);
    box-shadow: 0 0 1.5em rgb(231, 255, 52);
    padding: 0.6em;
  }
  100% {
    transform: rotate(360deg);
    border: 2px solid rgb(117, 52, 246);
    box-shadow: 0 0 1em rgb(117, 52, 246);
    padding: 0.4em;
  }
}

.music-start{
  border-spacing: 5em !important;
  -webkit-animation: 5s linear infinite spin;
  animation: 5s linear infinite spin;
}
</style>