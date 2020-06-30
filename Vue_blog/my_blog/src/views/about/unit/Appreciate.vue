<template>
  <div class="extra content">
    <!--                                    赞赏-->
    <div class="ui segment center aligned basic">
      <div id="pay_btn" class="ui button orange basic circular">赞赏</div>
    </div>
    <div
      class="ui flowing bottom center payQR popup transition m-padding-lr-tiny hidden"
      style="max-width: 500px !important;"
    >
      <div class="ui teal label basic">
        <div class="ui images" style="font-size: inherit !important;">
          <div class="image">
            <img
              class="ui image rounded bordered"
              src="http://cdn.panzvor.com/alipay.jpg"
              alt
              style="width: 100px;"
            />
            <div>支付宝</div>
          </div>
          <div class="image">
            <img
              class="ui image rounded bordered"
              src="http://cdn.panzvor.com/wechat_pay.png"
              alt
              style="width: 100px;"
            />
            <div>微信</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Appreciate",
  mounted() {
    $("#pay_btn").popup({
      popup: $(".payQR.popup"),
      position: "bottom center"
    });
  }
};
</script>

<style scoped>
.extra {
  padding: 0 !important;
}
.segment {
  margin-bottom: 0 !important;
}
</style>