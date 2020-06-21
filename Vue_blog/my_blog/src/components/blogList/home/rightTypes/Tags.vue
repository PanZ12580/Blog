<template>
  <div class="ui segments m-margin-top-medium m-bg">
    <div class="ui secondary segment m-bg">
      <div class="ui two column grid m-bg">
        <div class="column">
          <i class="tags icon teal"></i>
          <span>标签云</span>
        </div>
        <div class="column right aligned">
          <router-link to="/tags" class="m-teal" target="_self">
            more
            <i class="angle double right teal icon"></i>
          </router-link>
        </div>
      </div>
    </div>
    <div class="cloud m-bg">
      <ul>
        <li>
          <tags-item v-for="(tag,index) in tagsList" :key="index" :tagName="tag.name" :tagCount="tag.count"></tags-item>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>

export default {
  name: "Tags",
  components: {
    "tags-item": () => import('./TagsItem')
  },
  inject: ["tagsList"]
};
</script>

<style>
</style>