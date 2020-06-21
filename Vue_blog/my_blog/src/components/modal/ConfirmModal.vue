<template>
  <div class="ui basic modal">
    <div class="ui icon header">
      <i class="archive icon"></i>
      {{confirmMsg.title}}
    </div>
    <div class="content">
      <p>{{confirmMsg.msg}}</p>
    </div>
    <div class="actions">
      <div class="ui red basic cancel inverted button">
        <i class="remove icon"></i>
        否
      </div>
      <div class="ui green ok inverted button">
        <i class="checkmark icon"></i>
        是
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ConfirmModal",
  props: {
    confirmMsg: {
      type: Object,
      default() {
        return {};
      }
    }
  }
};
</script>

<style>
</style>