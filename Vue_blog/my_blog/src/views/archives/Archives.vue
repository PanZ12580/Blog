<template>
  <div id="main" class="m-padding-tb-max m-container-small">
    <div id="main_container" class="ui container">
      <common-header ref="header" :title="title" :count="totalBlog" :unit="unit"></common-header>
      <archives-list :archivesList="archivesList"></archives-list>
      <tool-bar ref="toolBar"></tool-bar>
    </div>
  </div>
</template>

<script>
import CommonHeader from "components/commonHeader/CommonHeader";

import { scrollEventListener, toolbarControl } from "common/mixin";
import { findArchivesBlogList } from "network/archivesAjax";

export default {
  name: "Archives",
  data() {
    return {
      title: "归档",
      totalBlog: 0,
      unit: "篇",
      archivesList: []
    };
  },
  components: {
    CommonHeader,
    "archives-list": () => import("./unit/ArchivesList")
  },
  activated() {
    this.getArchivesBlogList();
  },
  methods: {
    getArchivesBlogList() {
      findArchivesBlogList().then(res => {
        this.totalBlog = res.data.data.total;
        this.archivesList = res.data.data.list;
      });
    }
  },
  mixins: [scrollEventListener, toolbarControl]
};
</script>

<style>
</style>