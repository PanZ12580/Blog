<template>
  <router-link class="item" to="/categories">
    {{typeName}}
    <div class="ui orange basic label left pointing">{{typeCount}}</div>
  </router-link>
</template>

<script>
export default {
  name: "CategoriesItem",
  props: {
      typeName: String,
      typeCount: {
          type: Number,
          default: 0
      }
  }
};
</script>

<style>
</style>