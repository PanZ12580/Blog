<template>
  <main-content id="main-content">
    <template #blog_list>
      <div class="ui info large message">
        <h3>Hi,</h3>
        <p>PanzVor, 欢迎登录！</p>
      </div>
      <img src="http://cdn.panzvor.com/59558867.jpg" alt="" class="ui image rounded bordered fluid">
    </template>
  </main-content>
</template>
<script>

export default {
  name: "ProfileHome",
  components: { 
    "main-content": () => import("components/blogList/MainContent")
   }
};
</script>

<style scoped>
#main-content {
    padding-top: 3em !important;
}
</style>