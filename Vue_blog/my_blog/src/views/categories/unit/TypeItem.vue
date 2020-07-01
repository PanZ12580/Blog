<template>
  <div class="ui button labeled m-margin-tb-tiny" @click="itemClick" v-if="Object.keys(item).length !== 0">
    <div
      class="ui button m-hover link"
      :class="{active: item.id === selectedId}"
      v-text="item.name"
    ></div>
    <div
      class="ui basic left pointing label"
      :class="{active: item.id === selectedId}"
      v-text="item.blogCount"
    ></div>
  </div>
</template>

<script>
export default {
  name: "TagItem",
  props: {
    item: {
      type: Object,
      default() {
        return {
          id: 0,
          name: ''
        };
      }
    },
    selectedId: {
      type: Number,
      default: 0
    }
  },
  methods: {
    itemClick() {
      this.$emit("itemClick", this.item.id);
    }
  }
};
</script>

<style scoped>
.active.button {
  background-color: #db2828 !important;
  color: white !important;
  font-weight: 800 !important;
}
.active.label {
  border: 1.5px solid #db2828 !important;
}
</style>