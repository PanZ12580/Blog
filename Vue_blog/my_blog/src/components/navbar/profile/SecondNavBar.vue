<template>
  <!--二级导航-->
  <div class="ui attached pointing menu">
    <div class="ui container">
      <div class="right menu">
        <router-link to="/profile/blogPublish" class="m-teal item" :class="{active: isPublishActive}">发布</router-link>
        <router-link to="/profile/blogManage" class="m-teal item" :class="{active: isBlogManageActive}">列表</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SecondNavBar",
  computed: {
    isPublishActive() {
      return this.$route.path.indexOf("/blogPublish") != -1;
    },
    isBlogManageActive() {
      return this.$route.path.indexOf("/blogManage") != -1;
    }
  }
};
</script>

<style scoped>
.ui.attached.pointing.menu {
  margin-top: 70px;
}
</style>