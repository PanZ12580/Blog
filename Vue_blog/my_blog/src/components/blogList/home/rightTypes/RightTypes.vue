<template>
  <div class="five wide column">
    <categories>
      <template #default>
        <categories-item
          v-for="(type,index) in typesList"
          :key="index"
          :typeId="type.id"
          :typeName="type.name"
          :blogCount="type.blogCount"
        ></categories-item>
      </template>
    </categories>
    <tags v-bind="$attrs"></tags>
    <recommend v-bind="$attrs"></recommend>
    <qrcode></qrcode>
  </div>
</template>

<script>
export default {
  name: "RightTypes",
  props: {
    typesList: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  components: {
    categories: () => import("components/blogList/home/rightTypes/Categories"),
    recommend: () => import("components/blogList/home/rightTypes/Recommend"),
    "categories-item": () =>
      import("components/blogList/home/rightTypes/CategoriesItem"),
    tags: () => import("components/blogList/home/rightTypes/Tags"),
    qrcode: () => import("components/blogList/home/rightTypes/Qrcode")
  }
};
</script>

<style>
</style>