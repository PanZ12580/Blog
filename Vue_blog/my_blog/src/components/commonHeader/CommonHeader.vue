<template>
  <!--        头部-->
  <div id="content_header" class="ui top attached segment m-bg">
    <div class="ui two column grid middle aligned">
      <div class="column">
        <h3 class="ui teal header" v-text="title"></h3>
      </div>
      <div class="right aligned column">
        共
        <h2 class="ui orange header m-inline-block" v-text="blogCount"></h2>个
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CommonHeader",
  props: {
    blogCount: {
      type: Number,
      default: 0
    },
    title: String
  }
};
</script>

<style>
</style>