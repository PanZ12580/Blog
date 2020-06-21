<template>
  <router-link to="/tags">
    <span>{{tagName}}</span>
    <div class="m-inline-block">{{tagCount}}</div>
  </router-link>
</template>

<script>
export default {
  name: "TagsItem",
  props: {
    tagName: String,
    tagCount: {
      type: Number,
      default: 0
    }
  }
};
</script>

<style>
</style>