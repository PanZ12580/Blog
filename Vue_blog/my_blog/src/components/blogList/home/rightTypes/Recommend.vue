<template>
  <div class="ui segments m-margin-top-medium m-bg">
    <div class="ui secondary segment m-bg">
      <div class="ui">
        <i class="bookmark icon teal"></i>
        <span>最新推荐</span>
      </div>
    </div>
    <div class="ui teal segment m-bg">
      <div class="ui fluid menu vertical m-bg">
        <router-link
          :to="'/detail/' + item.id"
          target="_blank"
          class="item m-gray m-text-thin"
          v-for="(item,index) in recommendList"
          :key="index"
          v-text="item.title"
          :title="item.title"
        ></router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Recommend",
  props: {
    recommendList: {
      type: Array,
      default() {
        return [];
      }
    }
  }
};
</script>

<style scoped>
.m-text-thin {
  width: 100% !important;
  overflow: hidden !important;
  white-space: nowrap !important;
  text-overflow: ellipsis !important;
}
</style>