<template>
  <div class="ui message" v-show="isShow" :class="msgClass">
    <i class="close icon" @click="close"></i>
    <div class="header">提示：</div>
    <div v-if="msg">
      <p v-text="msg"></p>
    </div>
    <div v-if="msgList">
      <p v-for="(item, index) in msgList" :key="index" v-text="item"></p>
    </div>
  </div>
</template>

<script>
export default {
  name: "SuccessMsg",
  props: {
    msgClass: String,
    msg: String,
    msgList: {
      type: Array,
      default() {
        return []
      }
    },
    isShow: false
  },
  methods: {
    close() {
      this.$emit("close");
    }
  }
};
</script>

<style>
</style>