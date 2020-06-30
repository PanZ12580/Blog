<template>
  <div>
    <h4 class="ui horizontal divider header m-margin-top-medium">扫码加微信</h4>
    <div class="ui card centered" style="width: 11em;">
      <img v-lazy="'http://cdn.panzvor.com/wechat.png'" alt class="ui rounded image" />
    </div>
  </div>
</template>

<script>
export default {
  name: "Qrcode"
};
</script>

<style>
</style>