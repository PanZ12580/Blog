<template>
  <div class="ui attached padded segment m-bg">
    <blog-title v-bind="$attrs"></blog-title>
    <main-article v-bind="$attrs" v-on="$listeners"></main-article>
    <tag v-bind="$attrs"></tag>
    <appreciation v-if="appreciatable"></appreciation>
  </div>
</template>

<script>
import BlogTitle from "./BlogTitle";

export default {
  props: {
    appreciatable: true
  },
  name: "MainContent",
  components: {
    "main-article": () => import("./MainArticle"),
    tag: () => import("./Tag"),
    appreciation: () => import("./Appreciation"),
    BlogTitle
  }
};
</script>

<style>
</style>