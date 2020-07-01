<template>
  <div class="ui raised card">
    <a class="image" :href="link.url" target="_blank">
      <img v-lazy="link.picture" />
    </a>
    <div class="content">
      <a class="header center aligned" v-text="link.name" :href="link.url" target="_blank"></a>
    </div>
    <div class="extra content center aligned">
      <span v-text="fCreateTime"></span>
      <div class="ui two buttons">
        <div v-show="!link.allow" class="ui basic green button" @click="allowLink">允许</div>
        <div v-show="!link.allow" class="ui basic red button" @click="deleteLink">不允许</div>
        <div v-show="link.allow" class="ui basic red button" @click="deleteLink">删除</div>
      </div>
    </div>
    <confirm-modal :confirmMsg="confirmMsg" ref="confirm"></confirm-modal>
  </div>
</template>

<script>
import { formatDate } from "common/utils";
import { updateLink, deleteLink } from "network/profileAjax";

export default {
  name: "ManageCard",
  components: {
    "confirm-modal": () => import("components/modal/ConfirmModal")
  },
  data() {
    return {
      confirmMsg: {
        title: "删除友链",
        msg: "您正在准备删除一个友链，确定吗？"
      }
    };
  },
  props: {
    link: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  computed: {
    fCreateTime() {
      return formatDate(new Date(this.link.createTime), "yyyy-MM-dd hh:mm:ss");
    }
  },
  methods: {
    /**
     * 允许添加友链
     */
    allowLink() {
      this.link.allow = true;
      updateLink(this.link)
        .then(res => {
          if (res.data.flag) {
            this.$toast.show("操作成功");
          } else {
            this.$toast.show("操作失败" + res.data.errorMsg, false);
          }
        })
        .catch(err => {
          this.$toast.show("操作失败" + err, false);
        });
    },
    /**
     * 删除友链
     */
    deleteLink() {
      const _this = this;
      $(this.$refs.confirm.$el)
        .modal({
          onApprove: function() {
            deleteLink(_this.link.id)
              .then(res => {
                if (res.data.flag) {
                  _this.$toast.show("操作成功");
                } else {
                  _this.$toast.show("操作失败" + res.data.errorMsg, false);
                }
                _this.$emit("refresh");
              })
              .catch(err => {
                _this.$toast.show("操作失败" + err, false);
              });
          }
        })
        .modal("show");
    }
  }
};
</script>

<style scoped>
.center-content {
  text-align: center;
}
.content {
  position: relative;
}
.header {
  position: absolute;
  bottom: 0em;
  left: 50%;
  transform: translate(-50%, 0);
}
img {
  height: 15em !important;
}
</style>