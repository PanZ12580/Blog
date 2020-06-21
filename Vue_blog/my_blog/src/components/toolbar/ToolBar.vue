<template>
  <div
    id="toolbar"
    class="m-padding-tb-medium m-padding-lr-medium m-fixed m-right-bottom"
    style="display: none !important;"
  >
    <div class="ui vertical icon buttons">
      <slot></slot>
      <button @click="scrollToTop" class="ui icon button teal m-bg-teal link">
        <i class="chevron up icon large"></i>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ToolBar",
  methods: {
    scrollToTop() {
      document.getElementById("main_container").scrollIntoView({
        behavior: "smooth",
        block: "start",
        inline: "nearest"
      });
    }
  }
};
</script>

<style scoped>
#toolbar {
  z-index: 999 !important;
}
</style>