<template>
  <div
    id="content"
    class="typo js-toc-content typo-selection m-bg m-padding-lr-medium-responsive m-padding-tb-medium"
    v-html="content"
  ></div>
</template>

<script>
import("assets/lib/prism/prism.css");

export default {
  name: "MainArticle",
  props: {
    content: null
  },
  mounted() {
    this.appendScript();
    this.$emit("afterMounted");
  },
  methods: {
    appendScript() {
      let body = document.getElementsByTagName("body");
      let script = document.createElement("script");
      script.src = "http://qiniu.panzvor.com/prism.js";
      body[0].appendChild(script);
    }
  }
};
</script>

<style>
</style>