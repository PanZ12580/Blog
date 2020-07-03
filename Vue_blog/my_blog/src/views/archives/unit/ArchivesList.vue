<template>
  <div v-if="archivesList.length !== 0">
    <div v-for="(archive, index) in archivesList" :key="archive.year + '-' + index">
      <h2 class="ui header center aligned" v-text="archive.year"></h2>
      <div class="ui fluid vertical menu">
        <router-link
          target="_blank"
          class="item"
          v-for="(item, index) in archive.blogList"
          :key="item.id + '-' + index"
          :to="'/detail/' + item.id"
        >
          <div class="ui grid stackable">
            <div class="eleven wide column">
              <i class="mini teal icon circle over-title-icon"></i>
              <span
                class="m-margin-lr-small over-title"
                v-text="item.title"
                :title="item.title"
              ></span>
            </div>
            <div class="three wide column right aligned">
              <div
                class="ui label left pointing teal basic m-padding-tb-tiny m-text-thin"
                v-text="fCreateTime(item.createTime)"
              ></div>
            </div>
            <div class="two wide column right aligned">
              <div
                class="ui label left pointing orange m-padding-tb-tiny basic m-text-thin"
                v-text="item.flag"
              ></div>
            </div>
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { formatDate } from "common/utils";

export default {
  name: "ArchivesList",
  props: {
    archivesList: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  methods: {
    fCreateTime(createTime) {
      return formatDate(new Date(createTime), "MM月dd日");
    }
  }
};
</script>

<style scoped>
.ui.header {
  margin-top: 0.3em;
}
.over-title {
  max-width: 75% !important;
  overflow: hidden !important;
  white-space: nowrap !important;
  text-overflow: ellipsis !important;
  display: inline-block !important;
  position: absolute;
  top: 50%;
  transform: translate(0, -50%);
  margin-left: 1.5em !important;
}
.over-title-icon {
  position: absolute;
  top: 50%;
  transform: translate(0, -50%);
}
</style>