<template>
  <div class="ui attached segment m-bg" :class="attachClass">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: "MainList",
  props: {
    attachClass: {
      type: Array,
      default: () => ['']
    }
  }
};
</script>

<style>
</style>