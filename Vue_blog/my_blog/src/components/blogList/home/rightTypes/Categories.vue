<template>
  <!--分类-->
  <div class="ui segments m-bg">
    <div class="ui secondary segment m-bg">
      <div class="ui two column grid m-bg">
        <div class="column">
          <i class="idea icon teal"></i>
          <span>分类</span>
        </div>
        <div class="column right aligned">
          <router-link to="/categories" class="m-teal" target="_self">
            more
            <i class="angle double right teal icon"></i>
          </router-link>
        </div>
      </div>
      <div class="ui segment teal m-bg">
        <div class="ui fluid menu vertical m-bg">
          <slot></slot>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'Categories'
};
</script>

<style>
</style>