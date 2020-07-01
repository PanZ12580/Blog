<template>
  <div class="ui segment attached m-bg m-padding-tb-medium" v-if="tagList.length !== 0">
    <a
      v-for="item in tagList"
      :key="item.id"
      class="ui m-hover-orange m-gray basic left pointing label m-margin-tb-tiny large"
      @click="itemClick(item.id)"
      :class="{red: selectedId === item.id}"
    >
      <span v-text="item.name"></span>
      <div class="detail" v-text="item.blogCount"></div>
    </a>
  </div>
</template>

<script>
export default {
  name: "TagList",
  props: {
    tagList: {
      type: Array,
      default() {
        return [];
      }
    },
    selectedId: {
      type: Number,
      default: 0
    }
  },
  methods: {
    itemClick(tagId) {
      this.$emit("itemClick", tagId);
    }
  }
};
</script>

<style scoped>
</style>