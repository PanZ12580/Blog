<template>
  <div class="ui reply form">
    <div class="field">
      <textarea name="content" placeholder="请输入评论信息..."></textarea>
    </div>
    <div class="fields">
      <div class="field m-mobile-wide m-margin-bottom-small">
        <div class="ui left icon input">
          <i class="user icon"></i>
          <input type="text" name="name" placeholder="姓名" />
        </div>
      </div>
      <div class="field m-mobile-wide m-margin-bottom-small">
        <div class="ui left icon input">
          <i class="icon mail"></i>
          <input type="email" name="email" placeholder="邮箱" />
        </div>
      </div>
      <div class="field m-mobile-wide m-margin-bottom-small">
        <div class="ui blue labeled submit icon button m-mobile-wide">
          <i class="icon edit"></i> 添加 回复
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ReplyForm",
  mounted() {
    //    email popup
    $('[name="email"]').popup({
      on: "click",
      content: "有回复内容将通过此邮箱通知你",
      position: "top center"
    });
  }
};
</script>

<style>
</style>