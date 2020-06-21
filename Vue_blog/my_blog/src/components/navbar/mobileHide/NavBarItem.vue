<template>
  <router-link :to="to" class="item m-teal m-padding-lr-small" :class="{active: isActive}">
    <slot></slot>
  </router-link>
</template>

<script>
export default {
  name: "NavBarItem",
  props: {
    to: ''
  },
  computed: {
    isActive() {
      return this.$route.path.indexOf(this.to) !== -1;
    }
  }
};
</script>

<style>
</style>