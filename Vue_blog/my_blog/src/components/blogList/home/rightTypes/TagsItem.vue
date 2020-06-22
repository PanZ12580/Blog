<template>
  <router-link to="/tags">
    <span v-text="tagName"></span>
    <div class="m-inline-block" v-text="blogCount"></div>
  </router-link>
</template>

<script>
export default {
  name: "TagsItem",
  props: {
    tagName: String,
    blogCount: {
      type: Number,
      default: 0
    }
  }
};
</script>

<style scoped>
.m-inline-block {
  margin-left: 1em;
  font-size: 1.1em;
}
</style>