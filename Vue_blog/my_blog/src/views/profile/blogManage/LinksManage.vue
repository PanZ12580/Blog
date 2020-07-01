<template>
  <div id="main" class="m-padding-tb-max m-margin-top-small">
    <div class="container ui grid stackable">
      <div class="row">
        <div class="blank" v-if="linkList.length === 0">
          <div class="no-data">暂无友链</div>
        </div>
        <div v-else class="ui four cards stackable">
          <manage-card
            @refresh="refresh"
            v-for="(item, index) in linkList"
            :key="item.id + '-' + index"
            :link="item"
          ></manage-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { findLinkList } from "network/profileAjax";

export default {
  name: "LinksManage",
  data() {
    return {
      linkList: []
    };
  },
  components: {
    "manage-card": () => import("./unit/ManageCard")
  },
  activated() {
    this.getLinkList();
  },
  methods: {
    /**
     * 获取所有友链
     */
    getLinkList() {
      findLinkList().then(res => {
        this.linkList = res.data.data;
      });
    },
    /**
     * 刷新数据
     */
    refresh() {
      this.getLinkList();
    }
  }
};
</script>

<style scoped>
.blank {
  width: 100%;
}
.no-data {
  font-size: 3em;
  text-align: center;
  width: 100%;
  color: #c9c9c9;
  margin-top: 1em;
}
</style>