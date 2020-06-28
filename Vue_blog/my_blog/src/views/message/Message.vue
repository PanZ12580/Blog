<template>
  <div id="main" class="m-padding-tb-huge m-container">
    <div id="main_container" class="ui container stackable">
      <div ref="header"></div>
      <reply-form
        :parentCommentId="parentCommentId"
        :parentCommentNickname="parentCommentNickname"
        :parentComment="parentComment"
        @scrollToComment="scrollToComment"
        @resetParentComment="resetParentComment"
        @reRequestComment="reRequestComment"
        @openComment="openComment"
        ref="replyForm"
      ></reply-form>
      <div class="ui segment attached m-bg basic m-border-none" v-if="messageList.length !== 0">
        <div class="ui two cards stackable">
          <message-card
            v-for="(item, index) in messageList"
            :key="item.id + '-' + index"
            :message="item"
            :openMsgId="openMsgId"
            @reply="reply"
          ></message-card>
        </div>
      </div>
      <tool-bar ref="toolBar"></tool-bar>
    </div>
  </div>
</template>

<script>
import { scrollEventListener, toolbarControl } from "common/mixin";

import { findMessageList } from "network/messageAjax";

export default {
  name: "Message",
  data() {
    return {
      openMsgId: null,
      scrollToId: null,
      messageList: [],
      parentCommentId: null,
      parentCommentNickname: "",
      parentComment: null
    };
  },
  components: {
    "reply-form": () => import("components/comment/ReplyForm"),
    "message-card": () => import("./unit/MessageCard")
  },
  mixins: [scrollEventListener, toolbarControl],
  activated() {
    this.getMessageList();
  },
  methods: {
    getMessageList() {
      findMessageList().then(res => {
        this.messageList = res.data.data;
      });
    },
    /**
     * 回复
     */
    reply(comment) {
      this.scrollToId = comment.scrollTo;
      this.parentCommentId = comment.id;
      this.parentCommentNickname = comment.nickname;
      this.parentComment = comment.parentComment;
      this.$refs.replyForm.$el.scrollIntoView({
        behavior: "smooth",
        block: "end",
        inline: "nearest"
      });
      this.$refs.replyForm.$el.querySelector("textarea").focus();
    },
    /**
     * 滚动到评论区
     */
    scrollToComment() {
      if (this.scrollToId) {
        document.getElementById(this.scrollToId).scrollIntoView({
          behavior: "smooth",
          block: "start",
          inline: "nearest"
        });
      }
    },
    /**
     * 重置parentCommentId、parentCommentNickname
     */
    resetParentComment() {
      this.parentCommentId = null;
      this.parentCommentNickname = "";
      this.scrollToId = null;
      this.openMsgId = null;
    },
    /**
     * 重新请求评论数据
     */
    reRequestComment() {
      this.getMessageList();
    },
    /**
     * 回复成功后展开折叠评论
     */
    openComment(msgId) {
      this.openMsgId = msgId;
    }
  }
};
</script>

<style scoped>
</style>