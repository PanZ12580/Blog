<template>
  <div class="ui attached segment">
    <!--            图片区域-->
    <img class="ui image rounded fluid" alt v-lazy="firstPicture" />
  </div>
</template>

<script>
export default {
  name: "ContentImg",
  props: {
    firstPicture: ""
  }
};
</script>

<style scoped>
</style>