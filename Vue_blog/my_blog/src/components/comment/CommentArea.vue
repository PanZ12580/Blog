<template>
  <div id="comment_area" class="ui teal segment m-bg" v-if="commentList.length !== 0">
    <div class="ui threaded comments" style="max-width: 100% !important;">
      <h3 class="ui dividing header">评论</h3>
      <div class="comment" v-for="(item, index) in commentList" :key="item.id + '-' + index">
        <a class="avatar">
          <img :src="item.user.avatar" alt class="comment-avatar" />
        </a>
        <div class="content">
          <a class="author" @click="reply(item.id, item.user.nickname, item)">
            <span v-text="item.user.nickname"></span>
            <div class="ui basic left pointing label" v-if="item.adminComment">御主</div>
          </a>
          <div class="metadata">
            <span class="date" v-text="toFormatDate(item.createTime)"></span>
          </div>
          <div class="text" v-text="item.content"></div>
          <div class="actions">
            <a class="reply m_reply" @click="reply(item.id, item.user.nickname, item)">回复</a>
          </div>
        </div>
        <div class="comments" v-if="item.childComments && item.childComments.length !== 0">
          <div
            class="comment"
            v-for="(item2, index) in item.childComments"
            :key="item2.id + '-' + index"
          >
            <a class="avatar">
              <img :src="item2.user.avatar" class="comment-avatar" alt />
            </a>
            <div class="content">
              <span>
                <a
                  class="author"
                  v-text="item2.user.nickname"
                  @click="reply(item2.id, item2.user.nickname, item2)"
                ></a>
                <div class="ui basic left pointing label" v-if="item2.adminComment">御主</div>
                <span v-text="' @ ' + item2.parentComment.user.nickname" class="parent-comment"></span>
              </span>
              <div class="metadata">
                <span class="date" v-text="toFormatDate(item2.createTime)"></span>
              </div>
              <div class="text" v-text="item2.content"></div>
              <div class="actions">
                <a class="reply m_reply" @click="reply(item2.id, item2.user.nickname, item2)">回复</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { formatDate } from "common/utils";

export default {
  name: "CommentArea",
  props: {
    commentList: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  methods: {
    reply(id, nickname, parentComment) {
      this.$emit("reply", {
        id,
        nickname,
        parentComment
      });
    },
    /**
     * 时间格式化
     */
    toFormatDate(timestamp) {
      return formatDate(new Date(timestamp), "yyyy-MM-dd hh:mm:ss");
    }
  }
};
</script>

<style scoped>
.comment-avatar {
  margin-right: 0.25em !important;
  display: inline-block !important;
  width: 2em !important;
  height: 2em !important;
  border-radius: 500rem !important;
}
.parent-comment {
  color: #19d3cc;
  font-weight: 500;
}
.left.pointing.label {
  padding: 0.3em;
  color: #f3b31e;
  border-color: #f3b31e;
}
</style>