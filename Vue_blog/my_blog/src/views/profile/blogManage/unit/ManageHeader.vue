<template>
  <!--        头部-->
  <form class="ui equal width form segment m-margin-top-small m-bg">
    <div class="four fields inline doubling m-mobile-wide m_fields">
      <div class="field">
        <input type="text" name="title" placeholder="标题" v-model="title" />
      </div>
      <div class="field">
        <div class="ui labeled action input">
          <div class="ui selection dropdown">
            <i class="dropdown icon"></i>
            <div class="default text">分类</div>
            <div class="menu">
              <div
                class="item"
                v-for="(item, index) in typeList"
                :key="index"
                @click="select(item.id)"
              >{{item.name}}</div>
            </div>
          </div>
          <div class="button ui compact" @click="clear">清空</div>
        </div>
      </div>
      <div class="checkbox-field field">
        <div class="ui checkbox">
          <input type="checkbox" id="recommend" name="recommend" v-model="recommend" />
          <label for="recommend">推荐</label>
        </div>
      </div>
      <div class="field">
        <div class="ui teal basic button" @click="search">
          <i class="search icon"></i>搜索
        </div>
      </div>
    </div>
  </form>
</template>

<script>
import eventHandle from "common/event";

import { findTypes } from "network/profileAjax";

export default {
  name: "ManageHeader",
  data() {
    return {
      title: null,
      recommend: false,
      typeId: null,
      typeList: {
        type: Object,
        default() {
          return {};
        }
      }
    };
  },
  mounted() {
    $(".ui.dropdown").dropdown({
      on: "click"
    });
    this.getTypes();
  },
  activated() {
    this.getTypes();
    eventHandle.addEvent(window, "load", this.resizeForm);
    eventHandle.addEvent(window, "resize", this.resizeForm);
  },
  methods: {
    /**
     * 获取分类列表
     */
    getTypes() {
      findTypes(1, 0).then(res => {
        if (res.data.flag) {
          this.typeList = res.data.data.list.map(item => {
            let id = item.id;
            let name = item.name;
            return {
              id,
              name
            };
          });
        }
      });
    },
    /**
     * 分类选择事件
     */
    select(id) {
      this.typeId = id;
    },
    /**
     * 搜索事件
     */
    search() {
      this.$emit("search", {
        title: this.title === "" ? null : this.title,
        recommend: this.recommend,
        typeId: this.typeId
      });
    },
    /**
     * 清空分类搜索条件
     */
    clear() {
      $(".ui.selection.dropdown").dropdown("clear");
      this.typeId = null;
    },
    /**
     * 响应式监听
     */
    resizeForm() {
      let screenWeight = document.documentElement.clientWidth;
      if ($(".m_fields").hasClass("inline")) {
        if (screenWeight <= 800) {
          $(".m_fields").removeClass("inline");
        }
      } else if (screenWeight > 800) {
        $(".m_fields").addClass("inline");
      }
    }
  },
  deactivated() {
    eventHandle.removeEvent(window, "onload", this.resizeForm);
    eventHandle.removeEvent(window, "onresize", this.resizeForm);
  }
};
</script>

<style scoped>
.checkbox-field {
  margin-left: 1em !important;
}
</style>