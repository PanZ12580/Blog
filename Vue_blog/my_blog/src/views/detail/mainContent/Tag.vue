<template>
  <div class="m-padding-lr-medium-responsive" v-if="tagList.length !== 0">
    <div
      class="ui label teal basic left pointing"
      v-for="(item, index) in tagList"
      :key="item.name + '-' + index"
      v-text="item.name"
    ></div>
  </div>
</template>

<script>
export default {
  name: "Tag",
  props: {
    tagList: {
      type: Array,
      default() {
        return [];
      }
    }
  }
};
</script>

<style>
</style>