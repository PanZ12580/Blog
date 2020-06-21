<template>
  <div class="ui small modal">
    <div class="ui icon header">
      <i class="check icon teal" v-if="status"></i>
      <i class="x icon red" v-else></i>
      {{message}}
    </div>
    <div class="actions">
      <div class="ui green ok inverted button">
        <i class="checkmark icon"></i>
        确定
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Toast",
  data() {
    return {
      message: "",
      status: true
    };
  },
  methods: {
    show(message = "成功", status = true, duration = 2000) {
      this.message = message;
      this.status = status;
      $(".small.modal").modal("show");
      setTimeout(() => {
        $(".small.modal").modal("hide all");
        this.message = "";
        this.status = true;
      }, duration);
    }
  }
};
</script>

<style scoped>
</style>
