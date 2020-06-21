<template>
  <div class="ui grid stackable">
    <left-list></left-list>
    <right-types></right-types>
  </div>
</template>

<script>
export default {
  name: 'HomeList',
  components: {
    "left-list": () => import('components/blogList/home/leftList/LeftList'),
    "right-types": () => import('components/blogList/home/rightTypes/RightTypes')
  }
}
</script>

<style>

</style>