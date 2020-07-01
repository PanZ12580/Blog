<template>
  <a class="ui raised card" :href="linkItem.url" target="_blank">
    <div class="image">
      <img :src="linkItem.picture" />
    </div>
    <div class="content">
      <div class="header center aligned" v-text="linkItem.name"></div>
    </div>
    <div class="extra content center aligned">
      <span v-text="fCreateTime"></span>
    </div>
  </a>
</template>

<script>
import { formatDate } from "common/utils";

export default {
  name: "Card",
  props: {
    linkItem: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  computed: {
    fCreateTime() {
      return formatDate(new Date(this.linkItem.createTime), "yyyy-MM-dd");
    }
  }
};
</script>

<style scoped>
.center-content {
  text-align: center;
}
.content {
  position: relative;
}
.header {
  position: absolute;
  bottom: 0em;
  left: 50%;
  transform: translate(-50%, 0);
}
img {
  height: 15em !important;
}
</style>