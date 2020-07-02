<template>
  <table class="ui table teal celled unstackable selectable m-bg">
    <thead>
      <tr class="center aligned">
        <th class="m-teal" v-for="(item, index) in tableHeader" :key="index">{{item}}</th>
      </tr>
    </thead>
    <tbody>
      <tr class="center aligned" v-for="(item, index) in tableList" :key="index">
        <td>{{offset + index + 1}}</td>
        <td
          v-for="(value, index) in Object.values(item).slice(1)"
          :key="index"
          class="one-line"
          :title="value"
          v-text="value"
        ></td>
        <td>
          <div @click="edit(item.id)" class="ui teal basic button m_opt_btn">编辑</div>
          <div @click="remove(item.id)" class="ui orange basic button m_opt_btn">删除</div>
        </td>
      </tr>
    </tbody>
    <tfoot>
      <tr>
        <th colspan="8">
          <div class="ui mini pagination menu" v-show="!isFirstPage || !isLastPage">
            <a class="item m-teal" v-show="!isFirstPage" @click="lastPage">上一页</a>
            <a class="item m-teal" v-show="!isLastPage" @click="nextPage">下一页</a>
          </div>
          <div v-if="Object.keys(toOps).length !== 0">
            <router-link
              :to="toOps.to"
              class="ui button teal basic right floated m_opt_btn"
              tag="a"
            >{{toOps.ops}}</router-link>
          </div>
        </th>
      </tr>
    </tfoot>
  </table>
</template>

<script>
import eventHandle from "common/event";

export default {
  name: "ManageTable",
  data() {
    return {
      tableItem: {
        type: Object,
        default() {
          return {};
        }
      }
    };
  },
  computed: {
    offset() {
      return (this.currentPage - 1) * this.pageSize;
    }
  },
  props: {
    tableHeader: {
      type: Array,
      default() {
        return [];
      }
    },
    tableList: {
      type: Array,
      default() {
        return [];
      }
    },
    toOps: {
      type: Object,
      default() {
        return {};
      }
    },
    isFirstPage: {
      type: Boolean,
      default: true
    },
    isLastPage: {
      type: Boolean,
      default: false
    },
    currentPage: {
      type: Number
    },
    pageSize: {
      type: Number
    }
  },
  activated() {
    eventHandle.addEvent(window, "load", this.resizeForm);
    eventHandle.addEvent(window, "resize", this.resizeForm);
  },
  methods: {
    /**
     * 表单按钮响应式大小
     */
    resizeForm() {
      let screenWeight = document.documentElement.clientWidth;
      if (!$(".m_opt_btn").hasClass("mini")) {
        if (screenWeight <= 800) {
          $(".m_opt_btn").addClass("mini");
        }
      } else if (screenWeight > 800) {
        $(".m_opt_btn").removeClass("mini");
      }
    },
    /**
     * 翻页
     */
    lastPage() {
      this.$emit("toLastPage");
    },
    nextPage() {
      this.$emit("toNextPage");
    },
    /**
     * 编辑与删除
     */
    edit(id) {
      this.$emit("edit", id);
    },
    remove(id) {
      this.$emit("remove", id);
    }
  },
  deactivated() {
    eventHandle.removeEvent(window, "onload", this.resizeForm);
    eventHandle.removeEvent(window, "onresize", this.resizeForm);
  }
};
</script>

<style scoped>
.one-line {
  max-width: 15em !important;
  overflow: hidden !important;
  white-space: nowrap !important;
  text-overflow: ellipsis !important;
}
</style>