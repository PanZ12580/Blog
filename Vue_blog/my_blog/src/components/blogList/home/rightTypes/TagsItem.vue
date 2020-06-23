<template>
  <a class="tag-link" @click="toTag">
    <span v-text="tagName"></span>
    <div class="m-inline-block" v-text="blogCount"></div>
  </a>
</template>

<script>
export default {
  name: "TagsItem",
  props: {
    tagName: String,
    blogCount: {
      type: Number,
      default: 0
    },
    tagId: 0
  },
  methods: {
    toTag() {
      this.$router.push({
        name: "TagsWithId",
        params: {
          tagId: this.tagId
        }
      });
    }
  }
};
</script>

<style scoped>
.m-inline-block {
  margin-left: 1em;
  font-size: 1.1em;
}
.tag-link {
  cursor: pointer;
}
</style>