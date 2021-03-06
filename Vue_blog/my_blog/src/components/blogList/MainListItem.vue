<template>
  <div class="ui padded vertical segment m-padding-tb-big m-padding-lr-medium m-mobile-clear">
    <div class="ui red ribbon label" v-show="listItem.top">
      <i class="clipboard check icon"></i> 置顶
    </div>
    <div class="ui middle aligned mobile reversed stackable grid">
      <div class="eleven wide column">
        <router-link
          class="ui header blog-title m-hover-orange router-h3"
          v-text="listItem.title"
          target="_blank"
          :to="'/detail/' + listItem.id"
          tag="a"
        ></router-link>
        <p class="ui m-text over-text" v-text="listItem.description" :title="listItem.description"></p>
        <div class="ui stackable grid">
          <div class="eleven wide column top aligned">
            <div class="ui mini horizontal link list">
              <div class="item middle aligned">
                <img v-lazy="listItem.user.avatar" alt class="ui image avatar" />
                <div class="content">
                  <a target="_blank" class="header">
                    <span class="m-deep-teal m-hover-orange" v-text="listItem.user.nickname"></span>
                  </a>
                </div>
              </div>
              <div class="item middle aligned">
                <i class="calendar icon teal"></i>
                <span v-text="createTime"></span>
              </div>
              <div class="item middle aligned">
                <i class="eye icon teal"></i>
                <span v-text="listItem.views"></span>
              </div>
            </div>
          </div>
          <div class="five wide column right aligned middle aligned" v-if="listItem.type">
            <a
              @click="toCategory(listItem.type.id)"
              target="_blank"
              class="ui basic teal label m-padding-lr-tiny m-text-thin m-padding-tb-mini"
              v-text="listItem.type.name"
            ></a>
          </div>
          <div class="row" v-if="listItem.tagList && listItem.tagList.length !== 0">
            <div class="column">
              <div
                class="ui basic m-gray left pointing label m-padding-lr-tiny m-text-thin m-padding-tb-mini"
                v-for="item in listItem.tagList"
                :key="item.id + '-' + item.name"
                v-text="item.name"
                :class="{teal: selectedId === item.id}"
              ></div>
            </div>
          </div>
        </div>
      </div>
      <div class="five wide column">
        <a class="blog-picture" @click="toDetail(listItem.id)">
          <img alt class="ui rounded image" v-lazy="listItem.firstPicture" />
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import { formatDate } from "common/utils";

export default {
  name: "MainListItem",
  props: {
    listItem: Object,
    selectedId: Number
  },
  computed: {
    createTime() {
      return formatDate(new Date(this.listItem.createTime), "yyyy-MM-dd");
    }
  },
  methods: {
    toCategory(typeId) {
      this.$router.push({
        name: "CategoriesWithId",
        params: {
          typeId: typeId
        }
      });
    },
    toDetail(blogId) {
      this.$router.push({
        name: "Detail",
        params: { blogId }
      });
    }
  }
};
</script>

<style scoped>
.blog-title,
.blog-picture {
  cursor: pointer;
}
.blog-title:active {
  color: #c9c9c9;
}
.router-h3 {
  display: block;
  font-size: 1.28571429rem;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}
.ribbon {
  bottom: 1.5em;
}
.over-text {
  max-height: 8em !important;
  overflow: hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
}
</style>