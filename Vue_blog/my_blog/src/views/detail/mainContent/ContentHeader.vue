<template>
  <div id="content_header" class="ui top attached segment m-margin-top-small m-bg">
    <!--            头部-->
    <div class="ui horizontal link list">
      <div class="item middle aligned m-padding-lr-medium">
        <img :src="user.avatar" alt class="ui image avatar" />
        <div class="content">
          <a href="#" target="_blank" class="header">
            <span class="m-deep-teal" v-text="user.nickname"></span>
          </a>
        </div>
      </div>
      <div class="item middle aligned m-padding-lr-medium">
        <i class="calendar icon teal"></i>
        <span v-text="fCreateTime"></span>
      </div>
      <div class="item middle aligned m-padding-lr-medium">
        <i class="eye icon teal"></i>
        <span v-text="views"></span>
      </div>
    </div>
  </div>
</template>

<script>
import { formatDate } from "common/utils";

export default {
  name: "ContentHeader",
  props: {
    user: {
      type: Object,
      default() {
        return {};
      }
    },
    createTime: "",
    views: 0
  },
  computed: {
    fCreateTime() {
      return formatDate(new Date(this.createTime), "yyyy-MM-dd");
    }
  }
};
</script>

<style>
</style>