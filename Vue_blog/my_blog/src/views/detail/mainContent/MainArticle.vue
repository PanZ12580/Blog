<template>
  <div
    id="content"
    class="typo js-toc-content typo-selection m-bg m-padding-lr-medium-responsive m-padding-tb-medium"
    v-html="content"
  ></div>
</template>

<script>
import Prism from "assets/lib/prism/prism";
import("assets/lib/prism/prism.css");

export default {
  name: "MainArticle",
  props: {
    content: null
  },
  mounted() {
    this.$emit("afterMounted");
  },
  updated() {
    Prism.highlightAll();
    this.$emit("afterMounted");
  }
};
</script>

<style>
</style>