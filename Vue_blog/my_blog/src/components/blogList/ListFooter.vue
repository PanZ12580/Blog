<template>
  <div class="ui bottom attached segment m-bg">
    <div class="ui two column grid middle aligned">
      <div class="column">
        <span :class="{wrapper: !hasPreviousPage}">
          <div
            class="ui basic small button teal"
            @click="prePage"
            :class="{disabled: !hasPreviousPage}"
          >上一页</div>
        </span>
      </div>
      <div class="right aligned column">
        <span :class="{wrapper: !hasNextPage}">
          <div
            class="ui basic small button teal"
            @click="nextPage"
            :class="{disabled: !hasNextPage}"
          >下一页</div>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ListFooter",
  props: {
    hasPreviousPage: Boolean,
    hasNextPage: Boolean
  },
  methods: {
    prePage() {
      this.$emit("prePage");
    },
    nextPage() {
      this.$emit("nextPage");
    }
  }
};
</script>

<style scoped>
.wrapper {
  cursor: not-allowed;
  padding: 0;
}
</style>