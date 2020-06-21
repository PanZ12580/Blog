<template>
  <div id="main" class="m-padding-tb-max m-container-small">
    <div class="ui container">
      <form class="ui form">
        <div class="field required">
          <div class="ui left labeled input">
            <div class="labeled left ui input">
              <label class="ui compact teal basic label">分类</label>
              <input type="text" name="name" v-model="name" placeholder="分类名称" />
            </div>
          </div>
        </div>
        <div class="container ui right aligned">
          <div class="ui button" onclick="window.history.go(-1)">返回</div>
          <div
            class="ui teal button"
            @click="typeSubmit"
            @keyup.enter="typeSubmit"
            v-if="!isUpdate"
          >提交</div>
          <div
            class="ui teal button"
            @click="typeUpdate"
            @keyup.enter="typeUpdate"
            v-if="isUpdate"
          >更改</div>
        </div>
        <div class="ui error message"></div>
      </form>
    </div>
  </div>
</template>

<script>
import { saveType, findTypeById, updateType } from "network/profileAjax";

export default {
  name: "TypePublish",
  data() {
    return {
      name: "",
      isUpdate: false,
      id: Number
    };
  },
  methods: {
    /**
     * 新增分类
     */
    typeSubmit() {
      let validate = $(".ui.form").form("validate form");
      if (validate) {
        saveType(this.name).then(res => {
          if (res.data.flag) {
            this.$toast.show("提交成功");
            this.$router.push("/profile/typesManage");
          } else {
            this.$toast.show(res.data.errorMsg, false);
          }
        });
      }
    },
    /**
     * 根据id查询分类
     */
    getTypeById(id) {
      findTypeById(id).then(res => {
        if (res.data.flag) {
          this.name = res.data.data.name;
          this.isUpdate = true;
        } else {
          this.$toast.show(res.data.errorMsg, false)
        }
      });
    },
    /**
     * 更改分类名
     */
    typeUpdate() {
      let validate = $(".ui.form").form("validate form");
      if (validate) {
        const type = {
          id: this.id,
          name: this.name
        };
        updateType(type).then(res => {
          if (res.data.flag) {
            this.$toast.show("更改成功");
            this.$router.push("/profile/typesManage");
          } else {
            this.$toast.show(res.data.errorMsg, false);
          }
        });
      }
    },
  },
  mounted() {
    if (this.$route.params.id) {
      this.id = this.$route.params.id;
      this.isUpdate = true;
      this.name = this.getTypeById(this.id);
    }

    this.$nextTick(() => {
      $(".ui.form").form({
        fields: {
          title: {
            identifier: "name",
            rules: [
              {
                type: "empty",
                prompt: "分类: 请输入分类名称"
              }
            ]
          }
        }
      });
    });
  }
};
</script>

<style>
</style>