<template>
  <div class="ui bottom attached segment m-bg">
    <comment-area ref="commentArea" @reply="reply" v-bind="$attrs"></comment-area>
    <reply-form
      :parentCommentId="parentCommentId"
      :parentCommentNickname="parentCommentNickname"
      :parentComment="parentComment"
      :blogId="blogId"
      @scrollToComment="scrollToComment"
      @resetParentComment="resetParentComment"
      ref="replyForm"
      v-on="$listeners"
    ></reply-form>
  </div>
</template>

<script>
import CommentArea from "./CommentArea";
import ReplyForm from "./ReplyForm";

export default {
  name: "Comment",
  data() {
    return {
      parentCommentId: null,
      parentCommentNickname: "",
      parentComment: null
    };
  },
  props: {
    blogId: null
  },
  components: {
    CommentArea,
    ReplyForm
  },
  methods: {
    /**
     * 滚动到评论区
     */
    scrollToComment() {
      this.$refs.commentArea.$el.scrollIntoView({
        behavior: "smooth",
        block: "start",
        inline: "nearest"
      });
    },
    /**
     * 重置parentCommentId、parentCommentNickname
     */
    resetParentComment() {
      Object.assign(this.$data, this.$options.data());
    },
    reply(comment) {
      this.parentCommentId = comment.id;
      this.parentCommentNickname = comment.nickname;
      this.parentComment = comment.parentComment;
      this.$refs.replyForm.$el.scrollIntoView({
        behavior: "smooth",
        block: "end",
        inline: "nearest"
      });
      this.$refs.replyForm.$el.querySelector("textarea").focus();
    }
  }
};
</script>

<style>
</style>