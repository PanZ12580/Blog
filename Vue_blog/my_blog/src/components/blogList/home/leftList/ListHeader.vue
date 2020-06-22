<template>
  <div class="ui top attached segment m-bg">
    <div class="ui two column grid middle aligned">
      <div class="column">
        <h3 class="ui teal header">博客</h3>
      </div>
      <div class="right aligned column">
        共
        <h2 class="ui orange header m-inline-block" v-text="totalBlogs"></h2>篇
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ListHeader",
  props: {
    totalBlogs: 0
  }
};
</script>

<style scoped>
.ui.orange.header.m-inline-block{
  margin-left: 1em;
  margin-right: 1em;
}
</style>