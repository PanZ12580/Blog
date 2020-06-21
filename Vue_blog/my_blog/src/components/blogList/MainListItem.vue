<template>
  <div class="ui padded vertical segment m-padding-tb-big m-padding-lr-medium m-mobile-clear">
    <div class="ui mobile reversed stackable grid">
      <div class="eleven wide column">
        <h3 class="ui header">{{listItem.header}}</h3>
        <p
          class="ui m-text"
        >{{listItem.text}}</p>
        <div class="ui stackable grid">
          <div class="eleven wide column top aligned">
            <div class="ui mini horizontal link list">
              <div class="item middle aligned">
                <img v-lazy="listItem.headImg" alt class="ui image avatar" />
                <div class="content">
                  <a href="#" target="_blank" class="header">
                    <span class="m-deep-teal">{{listItem.author}}</span>
                  </a>
                </div>
              </div>
              <div class="item middle aligned">
                <i class="calendar icon teal"></i>
                <span>{{listItem.date}}</span>
              </div>
              <div class="item middle aligned">
                <i class="eye icon teal"></i>
                <span>{{listItem.views}}</span>
              </div>
            </div>
          </div>
          <div class="five wide column right aligned middle aligned">
            <a
              href="#"
              target="_blank"
              class="ui basic teal label m-padding-lr-tiny m-text-thin m-padding-tb-mini"
            >{{listItem.type}}</a>
          </div>
        </div>
      </div>
      <div class="five wide column">
        <a href="#" target="_blank">
          <img alt class="ui rounded image" v-lazy="listItem.imgSrc" />
        </a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MainListItem",
  props: {
      listItem: Object
  }
};
</script>

<style scoped>
/* .ui.rounded.image{
  width: 800px;
  height: 150px;
} */
</style>