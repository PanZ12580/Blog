<template>
  <div
    :id="message.id + message.createTime"
    class="card attached"
    v-if="Object.keys(message).length !== 0"
  >
    <div class="ui comments m-padding-tb-tiny">
      <div class="comment">
        <a class="avatar">
          <img :src="message.user.avatar" class="comment-avatar" />
        </a>
        <div class="content">
          <a class="author" @click="reply(message.id, message.user.nickname, message)">
            <span v-text="message.user.nickname"></span>
            <div class="ui basic left pointing label" v-if="message.adminMessage">御主</div>
          </a>
          <div class="metadata">
            <span class="date" v-text="toFormatDate(message.createTime)"></span>
            <span class="date" v-text="message.childMessagesCount + '条回复'"></span>
          </div>
          <div class="text" v-text="message.content"></div>
          <div class="actions">
            <a class="reply m_reply" @click="reply(message.id, message.user.nickname, message)">回复</a>
            <a @click="toggleCollapsed">
              <i class="expand icon"></i>
              显示或隐藏回复
            </a>
          </div>
        </div>
        <div
          class="comments"
          :class="{collapsed: isCollapsed}"
          v-if="message.childMessages.length !== 0"
        >
          <div
            class="comment"
            v-for="(item, index) in message.childMessages"
            :key="item.id + '-' + index"
          >
            <a class="avatar">
              <img :src="item.user.avatar" class="comment-avatar" />
            </a>
            <div class="content">
              <span>
                <a
                  class="author"
                  v-text="item.user.nickname"
                  @click="reply(item.id, item.user.nickname, item)"
                ></a>
                <div class="ui basic left pointing label" v-if="item.adminMessage">御主</div>
                <span v-text="' @ ' + item.parentMessage.user.nickname" class="parent-comment"></span>
              </span>
              <div class="metadata">
                <span class="date" v-text="toFormatDate(item.createTime)"></span>
              </div>
              <div class="text" v-text="item.content"></div>
              <div class="actions">
                <a class="reply m_reply" @click="reply(item.id, item.user.nickname, item)">回复</a>
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
  name: "MessageCard",
  data() {
    return {
      isCollapsed: true
    };
  },
  props: {
    message: {
      type: Object,
      default() {
        return {};
      }
    },
    openMsgId: Number
  },
  watch: {
    openMsgId() {
      if (this.openMsgId === this.message.id) {
        this.isCollapsed = false;
      }
    }
  },
  methods: {
    /**
     * 时间格式化
     */
    toFormatDate(timestamp) {
      return formatDate(new Date(timestamp), "yyyy-MM-dd hh:mm:ss");
    },
    /**
     * 评论折叠与隐藏
     */
    toggleCollapsed() {
      this.isCollapsed = !this.isCollapsed;
    },
    /**
     * 回复
     */
    reply(id, nickname, parentComment) {
      this.$emit("reply", {
        scrollTo: this.message.id + this.message.createTime,
        id,
        nickname,
        parentComment
      });
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
@media only screen and (max-width: 800px) {
  .avatar {
    margin-left: 0.5em !important;
  }
}
.card {
  padding: 1em !important;
}
.parent-comment {
  color: #6fa1ec;
  font-weight: 600;
}
.left.pointing.label {
  padding: 0.3em;
  color: #f3b31e;
  border-color: #f3b31e;
}
</style>