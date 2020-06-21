<template>
  <main-content id="main-content">
    <template #blog_list>
      <div class="ui info large message">
        <h3>500，服务器出现异常！</h3>
      </div>
    </template>
  </main-content>
</template>
<script>
import MainContent from "components/blogList/MainContent";

export default {
  name: "ServerError",
  components: {
    MainContent
  },
  activated() {
    $(".fixed.menu").transition("fade in");
  },
  deactivated() {
    $(".fixed.menu").transition("fade out");
  }
};
</script>

<style scoped>
#main-content {
  padding-top: 10em !important;
}
</style>