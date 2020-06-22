<template>
  <div class="ui padded vertical segment m-padding-tb-big m-padding-lr-medium m-mobile-clear">
    <div class="ui middle aligned mobile reversed stackable grid">
      <div class="eleven wide column">
        <h3 class="ui header" v-text="listItem.title"></h3>
        <p class="ui m-text" v-text="listItem.description"></p>
        <div class="ui stackable grid">
          <div class="eleven wide column top aligned">
            <div class="ui mini horizontal link list">
              <div class="item middle aligned">
                <!-- <img v-lazy="listItem.user.avatar" alt class="ui image avatar" /> -->
                <div class="content">
                  <a target="_blank" class="header">
                    <!-- <span class="m-deep-teal" v-text="listItem.user.nickname"></span> -->
                  </a>
                </div>
              </div>
              <div class="item middle aligned">
                <i class="calendar icon teal"></i>
                <span v-text="createTime"></span>
              </div>
              <div class="item middle aligned">
                <i class="eye icon teal"></i>
                <span v-text="listItem.views"></span>
              </div>
            </div>
          </div>
          <div class="five wide column right aligned middle aligned">
            <a
              href="#"
              target="_blank"
              class="ui basic teal label m-padding-lr-tiny m-text-thin m-padding-tb-mini"
              v-text="listItem.type.name"
            ></a>
          </div>
        </div>
      </div>
      <div class="five wide column">
        <a href="#" target="_blank">
          <img alt class="ui rounded image" v-lazy="listItem.firstPicture" />
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import { formatDate } from "common/utils";

export default {
  name: "MainListItem",
  props: {
    listItem: Object
  },
  computed: {
    createTime() {
      return formatDate(new Date(this.listItem.createTime), "yyyy-MM-dd")
    }
  }
};
</script>

<style scoped>
/* .ui.rounded.image{
  width: 800px;
  height: 150px;
} */
</style>