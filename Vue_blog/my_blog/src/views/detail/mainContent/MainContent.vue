<template>
  <div class="ui attached padded segment m-bg">
    <content-header></content-header>
    <main-article></main-article>
    <tag></tag>
    <appreciation></appreciation>
  </div>
</template>

<script>

export default {
  name: "MainContent",
  components: {
    "content-header": () => import("./ContentHeader"),
    "main-article": () => import("./MainArticle"),
    "tag": () => import("./Tag"),
    "appreciation": () => import("./Appreciation")
  }
};
</script>

<style>
</style>