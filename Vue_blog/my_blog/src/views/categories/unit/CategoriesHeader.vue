<template>
  <!--        头部-->
  <div id="content_header" class="ui top attached segment m-bg">
    <div class="ui two column grid middle aligned">
      <div class="column">
        <h3 class="ui teal header">分类</h3>
      </div>
      <div class="right aligned column">
        共
        <h2 class="ui orange header m-inline-block">&nbsp;{{count}}&nbsp;</h2>个
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CategoriesHeader",
  props: {
    count: {
      type: Number,
      default: 0
    }
  }
};
</script>

<style>
</style>