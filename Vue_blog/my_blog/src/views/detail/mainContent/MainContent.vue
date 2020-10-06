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
import MainArticle from "./MainArticle"

export default {
  props: {
    appreciatable: true
  },
  name: "MainContent",
  components: {
    MainArticle,
    tag: () => import("./Tag"),
    appreciation: () => import("./Appreciation"),
    BlogTitle
  }
};
</script>

<style>
</style>