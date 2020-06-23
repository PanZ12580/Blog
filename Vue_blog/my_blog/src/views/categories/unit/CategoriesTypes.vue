<template>
  <div class="ui segment attached m-bg m-padding-tb-medium" v-if="typesList.length !== 0">
    <type-item
      v-for="item in typesList"
      :key="item.id"
      :item="item"
      v-bind="$attrs"
      v-on="$listeners"
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
  components: { TypeItem }
};
</script>

<style scoped>
</style>