<template>
  <div
    id="main"
    class="m-padding-tb-max m-container-small animate__animated animate__fadeIn line-numbers"
  >
    <div id="main_container" class="ui container">
      <content-header
        ref="header"
        :user="blog.user"
        :createTime="blog.createTime"
        :views="blog.views"
      ></content-header>
      <content-img :firstPicture="blog.firstPicture"></content-img>
      <main-content
        :content="blog.content"
        :tagList="blog.tagList"
        :title="blog.title"
        :flag="blog.flag"
        :appreciatable="blog.appreciation"
        @afterMounted="initToolbar"
      ></main-content>
      <blog-message
        v-if="blog.shareStatement"
        :user="blog.user"
        :createTime="blog.createTime"
        :updateTime="blog.updateTime"
      ></blog-message>
      <comment
        @reRequestComment="reRequestComment"
        ref="comment"
        v-if="blog.commentabled"
        :blogId="id"
        :commentList="commentList"
      ></comment>
    </div>
    <tool-bar ref="toolBar">
      <template #default>
        <div id="toc_btn" class="ui button teal m-bg-teal">目录</div>
        <a @click="toMessage" class="ui teal button m-bg-teal" v-if="blog.commentabled">留言</a>
        <div class="ui icon button wechat_qr teal m-bg-teal">
          <i class="ui icon large qrcode"></i>
        </div>
      </template>
    </tool-bar>
    <!--    目录-->
    <div
      class="ui js-toc flowing toc_container popup transition m-padding-lr-tiny hidden"
      style="width: 250px !important;max-width: 500px !important;"
    ></div>
    <!--    网站二维码-->
    <div
      id="qrcode"
      class="ui wechat flowing popup top left transition hidden"
      style="width: 120px;"
    >
      <img
        :src="'http://qr.liantu.com/api.php?text='+url"
        class="ui rounded image"
        style="width: 120px !important;"
      />
      <div style="text-align:center;font-size:12px">
        <span>扫一扫</span>
        <p>使用手机阅读</p>
      </div>
    </div>
  </div>
</template>

<script>
import ContentHeader from "./mainContent/ContentHeader";

import tocbot from "assets/js/tocbot.min";
import {
  NoAliveScrollEventListener,
  NoAliveToolbarControl
} from "common/mixin";
import { findBlogDetail } from "network/detailAjax";
import { findCommentListByBlogId } from "network/commentAjax";

export default {
  name: "Detail",
  data() {
    return {
      id: 0,
      blog: {
        type: Object,
        default() {
          return {};
        }
      },
      commentList: [],
      url: "http://www.panzvor.com"
    };
  },
  components: {
    ContentHeader,
    "content-img": () => import("./mainContent/ContentImg"),
    "main-content": () => import("./mainContent/MainContent"),
    "blog-message": () => import("./mainContent/BlogMessage"),
    comment: () => import("components/comment/Comment")
  },
  async mounted() {
    document.querySelector("#app-scroll").scrollIntoView({
      behavior: "smooth",
      block: "start",
      inline: "nearest"
    });
    if (this.$route.params.blogId) {
      this.id = this.$route.params.blogId;
    }
    await this.getBlogDetail();
    this.url += this.$route.path;
    //    网站二维码
    $(".wechat_qr").popup({
      popup: $(".wechat.popup"),
      position: "left center",
      hoverable: true
    });
  },
  methods: {
    /**
     * 功能条目录初始化
     */
    initToolbar() {
      tocbot.init({
        tocSelector: ".js-toc",
        contentSelector: ".js-toc-content",
        headingSelector: "h1, h2, h3"
      });
      //    目录
      $("#toc_btn").popup({
        popup: $(".toc_container"),
        on: "click",
        position: "left center"
      });
    },
    /**
     * 根据Id获取博客详情以及对应的评论列表
     */
    async getBlogDetail() {
      await findBlogDetail(this.id)
        .then(res => {
          this.blog = res.data.data;
          return res.data.data.id;
        })
        .then(blogId => {
          findCommentListByBlogId(blogId).then(res => {
            this.commentList = res.data.data;
          });
        });
    },
    /**
     * 成功插入新的评论后重新请求评论列表
     */
    reRequestComment() {
      findCommentListByBlogId(this.id).then(res => {
        this.commentList = res.data.data;
      });
    },
    /**
     * 功能条点击跳转到评论区
     */
    toMessage() {
      this.$refs.comment.$el.scrollIntoView({
        behavior: "smooth",
        block: "start",
        inline: "nearest"
      });
    }
  },
  mixins: [NoAliveScrollEventListener, NoAliveToolbarControl]
};
</script>

<style scoped>
@import "~assets/css/typo.css";
@import "~assets/css/tocbot.css";
#main {
  position: -webkit-sticky;
  position: sticky;
  z-index: 1;
}
</style>