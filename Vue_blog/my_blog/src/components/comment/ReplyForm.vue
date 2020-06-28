<template>
  <div class="ui reply form">
    <div class="field">
      <textarea name="content" :placeholder="placeholderMsg" v-model="content"></textarea>
    </div>
    <div class="fields">
      <div class="field m-mobile-wide m-margin-bottom-small">
        <div class="ui left icon input" :class="{disabled: hasUser}">
          <i class="user icon"></i>
          <input type="text" name="nickname" placeholder="姓名" v-model="user.nickname" />
        </div>
      </div>
      <div class="field m-mobile-wide m-margin-bottom-small">
        <div class="ui left icon input">
          <i class="icon mail"></i>
          <input type="email" name="email" placeholder="邮箱" v-model="user.email" />
        </div>
      </div>
      <div class="field m-mobile-wide m-margin-bottom-small" v-if="parentCommentId">
        <div class="ui labeled icon button m-mobile-wide" @click="cancelReply">
          <i class="icon close"></i> 取消 回复
        </div>
      </div>
      <div class="field m-mobile-wide m-margin-bottom-small">
        <div class="ui blue labeled icon button m-mobile-wide" @click="submitForm">
          <i class="icon edit"></i> 添加 回复
        </div>
      </div>
    </div>
    <div class="ui error message"></div>
  </div>
</template>

<script>
import { saveComment } from "network/commentAjax";
import { findUserMsg } from "network/userAjax";

import { saveMessage } from "network/messageAjax";

export default {
  name: "ReplyForm",
  data() {
    return {
      hasUser: false,
      content: "",
      user: {
        type: Object,
        default() {
          return {
            nickname: "",
            email: ""
          };
        }
      }
    };
  },
  props: {
    blogId: null,
    parentCommentId: null,
    parentCommentNickname: null,
    parentComment: null
  },
  computed: {
    placeholderMsg() {
      return this.parentCommentNickname
        ? "@" + this.parentCommentNickname + "："
        : "请输入评论信息...";
    }
  },
  mounted() {
    this.getUserMsg();
    // nickname popup
    $('[name="nickname"]').popup({
      on: "click",
      content: "此名称将作为您在本站的常用名，不支持再修改~~",
      position: "top center"
    });
    //    email popup
    $('[name="email"]').popup({
      on: "click",
      content: "有回复内容将通过此邮箱通知你",
      position: "top center"
    });
    // 表单校验规则
    $(".ui.form").form({
      fields: {
        content: {
          identifier: "content",
          rules: [
            {
              type: "empty",
              prompt: "请输入评论内容"
            }
          ]
        },
        nickname: {
          identifier: "nickname",
          rules: [
            {
              type: "empty",
              prompt: "请输入名称"
            }
          ]
        },
        email: {
          identifier: "email",
          rules: [
            {
              type: "email",
              prompt: "请输入正确的邮箱"
            }
          ]
        }
      }
    });
  },
  methods: {
    /**
     * 获取用户信息
     */
    getUserMsg() {
      findUserMsg().then(res => {
        if (res.data.data) {
          this.user = res.data.data;
          this.hasUser = true;
        }
      });
    },
    /**
     * 表单提交
     */
    submitForm() {
      let validate = $(".ui.form").form("validate form");
      if (validate) {
        if (this.blogId) {
          this.commentSubmit();
        } else {
          this.messageSubmit();
        }
      } else {
        console.log("校验失败");
      }
    },
    /**
     * 博客内评论提交
     */
    commentSubmit() {
      const comment = {
        blogId: this.blogId,
        parentCommentId: this.parentCommentId,
        content: this.content,
        user: this.user,
        parentComment: this.parentComment
      };
      saveComment(comment).then(res => {
        this.$emit("scrollToComment");
        this.$emit("resetParentComment");
        this.$emit("reRequestComment");
        this.resetData();
        this.getUserMsg();
      });
    },
    /**
     * 留言区留言或评论提交
     */
    messageSubmit() {
      const message = {
        parentMsgId: this.parentCommentId,
        content: this.content,
        user: this.user,
        parentMessage: this.parentComment
      };
      saveMessage(message).then(res => {
        this.$emit("scrollToComment");
        this.$emit("resetParentComment");
        this.$emit("reRequestComment");
        this.$emit("openComment", this.parentCommentId);
        this.resetData();
        this.getUserMsg();
      });
    },
    /**
     * 回复取消
     */
    cancelReply() {
      this.content = "";
      this.$emit("resetParentComment");
    },
    /**
     * 重置data
     */
    resetData() {
      Object.assign(this.$data, this.$options.data());
    }
  }
};
</script>

<style>
</style>