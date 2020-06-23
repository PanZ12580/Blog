<template>
  <router-link class="item" :to="'/categories/' + typeId" v-text="typeName" tag="a">
    <div class="ui orange basic label left pointing" v-text="blogCount"></div>
  </router-link>
</template>

<script>
export default {
  name: "CategoriesItem",
  props: {
    typeName: String,
    blogCount: {
      type: Number,
      default: 0
    },
    typeId: 0
  }
};
</script>

<style>
</style>