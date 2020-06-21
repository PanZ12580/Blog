<template>
  <div>
    <profile-nav-bar></profile-nav-bar>
    <second-nav-bar></second-nav-bar>
    <keep-alive :exclude="/.*Publish/">
      <router-view v-show="isShow" :key="$router.fullPath"></router-view>
    </keep-alive>
  </div>
</template>

<script>
export default {
  name: "Profile",
  data() {
    return {
      isShow: true
    };
  },
  mounted() {
    this.$bus.$on("reloadEdit", this.reload);
  },
  deactivated() {
    this.$bus.$off("reload", this.reload);
  },
  beforeDestroy() {
    this.$bus.$off("reload", this.reload);
  },
  methods: {
    reload() {
      this.isShow = false;
      console.log("reload");
      this.$nextTick(() => {
        this.isShow = true;
      });
    }
  },
  components: {
    "profile-nav-bar": () => import("components/navbar/ProfileNavBar"),
    "second-nav-bar": () => import("components/navbar/profile/SecondNavBar")
  },
  activated() {
    $(".fixed.menu").transition("fade in");
    this.$bus.$on("hideNav", this.navVisival);
  },
  deactivated() {
    $(".fixed.menu").transition("fade out");
    this.$bus.$off("hideNav", this.navVisival);
  },
  methods: {
    navVisival(status) {
      if (status) {
        $(".fixed.menu").transition("fade out");
      } else {
        $(".fixed.menu").transition("fade in");
      }
    }
  }
};
</script>

<style>
</style>