<template>
  <div id="main" class="m-padding-tb-max m-margin-top-small">
    <div class="container ui grid stackable">
      <div class="row apply-row">
        <div class="column right aligned">
          <apply></apply>
        </div>
      </div>
      <div class="ui horizontal divider header">友情链接</div>
      <div class="row">
        <div class="blank" v-if="allowedLinkList.length === 0">
          <img src="http://cdn.panzvor.com/img/20200701182004.jpg" alt class="no-data-image" />
          <div class="no-data">卑微求入驻orz</div>
        </div>
        <div v-else class="ui four link cards stackable">
          <card
            v-for="(item, index) in allowedLinkList"
            :key="item.link + '-' + index"
            :linkItem="item"
          ></card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Card from "./unit/Card";

import { scrollEventListener } from "common/mixin";
import { findAllowedLinkList } from "network/linkAjax";

export default {
  name: "Links",
  data() {
    return {
      allowedLinkList: []
    };
  },
  mixins: [scrollEventListener],
  components: {
    Card,
    apply: () => import("./unit/Apply")
  },
  activated() {
    this.getAllowedLinkList();
  },
  methods: {
    getAllowedLinkList() {
      findAllowedLinkList().then(res => {
        this.allowedLinkList = res.data.data;
      });
    }
  }
};
</script>

<style scoped>
.apply-row {
  margin-top: 1.5em !important;
  margin-bottom: 1em !important;
}
.blank {
  width: 100%;  
}
.no-data {
  font-size: 3em;
  text-align: center;
  width: 100%;
  color: #c9c9c9;
  margin-top: 2em;
}
.no-data-image {
  display: block;
  margin: auto;
  opacity: 0.5;
}
.four.link.cards {
  width: 100%;
}
</style>