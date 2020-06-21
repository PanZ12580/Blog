<template>
  <main-content id="main-content">
    <template #blog_list>
      <div class="ui info large message">
        <h3>401，缺乏身份验证凭证！</h3>
      </div>
    </template>
  </main-content>
</template>
<script>
import MainContent from "components/blogList/MainContent";
export default {
  name: "UnAuth",
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