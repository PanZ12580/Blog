<template>
  <div class="ui button labeled m-margin-tb-tiny" @click="itemClick">
    <div class="ui button m-hover link" :class="{active: currentIndex === index}">{{typeName}}</div>
    <div class="ui basic left pointing label" :class="{active: currentIndex === index}">{{typeCount}}</div>
  </div>
</template>

<script>
export default {
  name: "TagItem",
  props: {
    typeName: {
      type: String,
      default: ""
    },
    typeCount: {
      type: Number,
      default: 0
    },
    currentIndex: {
      type: Number,
      default: 0
    },
    index: {
      type: Number,
      default: 0
    }
  },
  methods: { 
    itemClick() {
      this.$emit("itemClick")
    }
   }
};
</script>

<style scoped>
.active.button {
  background-color: #19d3cc !important;
  color: white !important;
  font-weight: 800 !important;
}
.active.label {
  border: 1.5px solid #19d3cc !important;
}
</style>