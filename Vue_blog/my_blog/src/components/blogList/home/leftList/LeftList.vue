<template>
  <div class="eleven wide column m-bg">
    <list-header></list-header>
    <main-list>
      <template #default>
        <main-list-item v-for="(item,index) in mainList" :key="index" :listItem="item"></main-list-item>
      </template>
    </main-list>
    <list-footer></list-footer>
  </div>
</template>

<script>

export default {
  name: "LeftList",
  inject: ["mainList"],
  components: {
    "list-header": () => import("components/blogList/home/leftList/ListHeader"),
    "main-list": () => import("components/blogList/MainList"),
    "main-list-item": () => import("components/blogList/MainListItem"),
    "list-footer": () => import("components/blogList/ListFooter")
  }
};
</script>

<style>
</style>