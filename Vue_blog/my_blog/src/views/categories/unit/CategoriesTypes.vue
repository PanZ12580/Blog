<template>
  <div class="ui segment attached m-bg m-padding-tb-medium">
    <type-item
      v-for="(type,index) in typesList"
      :key="index"
      :typeName="type.name"
      :typeCount="type.count"
      :index="index"
      :currentIndex="currentIndex"
      @itemClick="itemClick(index)"
    ></type-item>
  </div>
</template>

<script>
import TypeItem from "./TypeItem";

export default {
  name: "CategoriesTypes",
  props: {
    typesList: Array
  },
  data() {
    return {
      currentIndex: 0
    };
  },
  components: { TypeItem },
  methods: {
    itemClick(index) {
      this.currentIndex = index;
    }
  }
};
</script>

<style>
</style>