<template>
  <div class="side m-shape-side stackable container ui">
    <div class="ui container stackable card fluid">
      <div class="image mini">
        <img src="~assets/images/76556159_p0.jpg" />
      </div>
      <div class="content bottom bottom attached">
        <a class="header m-hover-teal" v-text="aboutTitle"></a>
        <div class="description" v-html="aboutContent"></div>
      </div>
      <slot name="extra"></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: "Side",
  props: {
    aboutTitle: "",
    aboutContent: ""
  }
};
</script>

<style scoped>
.description {
  letter-spacing: 0.2em;
  line-height: 2em;
}
.description:not(h4) {
  text-align: left;
}
</style>