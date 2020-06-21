<template>
  <div class="five wide column">
    <categories>
      <template #default>
        <categories-item v-for="(type,index) in typesList" :key="index" :typeName="type.name" :typeCount="type.count"></categories-item>
      </template>
    </categories>
    <tags></tags>
    <recommend></recommend>
    <qrcode></qrcode>
  </div>
</template>

<script>
export default {
  name: 'RightTypes',
  components: {
    "categories": () => import('components/blogList/home/rightTypes/Categories'),
    "recommend": () => import('components/blogList/home/rightTypes/Recommend'),
    "categories-item": () => import('components/blogList/home/rightTypes/CategoriesItem'),
    "tags": () => import('components/blogList/home/rightTypes/Tags'),
    "qrcode": () => import('components/blogList/home/rightTypes/Qrcode')
  },
  inject: [
    'typesList'
  ]
}
</script>

<style>

</style>