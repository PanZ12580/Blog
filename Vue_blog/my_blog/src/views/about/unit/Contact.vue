<template>
  <div class="extra content">
    <div class="ui three column m-padding-tb-tiny grid middle aligned center">
      <a
        href="https://github.com/Panz12580"
        target="_blank"
        class="column aligned center github-icon"
      >
        <img src="~assets/images/common/github.png" alt class="ui image mini" />
      </a>
      <div class="column aligned center">
        <a class="qq-icon">
          <img src="~assets/images/common/QQ.png" alt class="ui image mini" />
        </a>
      </div>
      <div class="column aligned center">
        <a class="wechat-icon">
          <img src="~assets/images/common/wechat.png" alt class="ui image mini" />
        </a>
      </div>
    </div>
    <div class="ui QQ flowing popup top left transition hidden m-padding-lrtb">
      <img src="~assets/images/qq.jpg" class="image ui rounded" style="width: 120px;" />
    </div>
    <div class="ui wechat flowing popup top left transition hidden m-padding-lrtb">
      <img src="~assets/images/wechat.png" class="image ui rounded" style="width: 120px;" />
    </div>
  </div>
</template>

<script>
export default {
  name: "Contact",
  mounted() {
    $(".github-icon").popup({
      inline: true,
      content: "去我的Github",
      position: "bottom center"
    });
    $(".qq-icon").popup({
      popup: $(".QQ.popup"),
      position: "bottom center",
      hoverable: true
    });
    $(".wechat-icon").popup({
      popup: $(".wechat.popup"),
      position: "bottom center",
      hoverable: true
    });
  },
};
</script>

<style scoped>
</style>