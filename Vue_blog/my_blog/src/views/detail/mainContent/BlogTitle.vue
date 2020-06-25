<template>
  <div>
    <div class="ui segment basic right aligned">
      <div class="ui label basic orange" v-text="flag"></div>
    </div>
    <h2 class="ui center aligned header" v-text="title"></h2>
  </div>
</template>

<script>
export default {
  name: "BlogTitle",
  props: {
    title: "",
    flag: ""
  }
};
</script>

<style>
</style>