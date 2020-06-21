<template>
  <div class="right menu item m-mobile-hide">
    <div class="ui dropdown item">
      <div class="text">
        <img
          class="image ui avatar"
          alt
          src="~assets/images/59558867.jpg"
          style="width: 30px !important; height: 30px!important;"
        />
        <span class="m-teal">PanzVor</span>
      </div>
      <i class="icon teal dropdown"></i>
      <div class="menu">
        <a class="item" @click="logout">注销</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RightMenu",
  mounted() {
    $(".ui.dropdown").dropdown({
      on: "click"
    });
  },
  methods: {
    logout() {
      this.$emit("logout");
    }
  }
};
</script>

<style>
</style>