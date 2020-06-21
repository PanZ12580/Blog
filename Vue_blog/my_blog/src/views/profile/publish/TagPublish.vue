<template>
  <div id="main" class="m-padding-tb-max m-container-small">
    <div class="ui container">
      <form class="ui form">
        <div class="field required">
          <div class="ui left labeled input">
            <div class="labeled left ui input">
              <label class="ui compact teal basic label">标签</label>
              <input type="text" name="name" v-model="name" placeholder="标签名称" />
            </div>
          </div>
        </div>
        <div class="container ui right aligned">
          <div class="ui button" onclick="window.history.go(-1)">返回</div>
          <div
            class="ui teal button"
            @click="tagSubmit"
            @keyup.enter="tagSubmit"
            v-if="!isUpdate"
          >提交</div>
          <div class="ui teal button" @click="tagUpdate" @keyup.enter="tagUpdate" v-if="isUpdate">更改</div>
        </div>
        <div class="ui error message"></div>
      </form>
    </div>
  </div>
</template>

<script>
import { saveTag, findTagById, updateTag } from "network/profileAjax";

export default {
  name: "TagPublish",
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
    tagSubmit() {
      let validate = $(".ui.form").form("validate form");
      if (validate) {
        saveTag(this.name).then(res => {
          if (res.data.flag) {
            this.$toast.show("提交成功");
            this.$router.push("/profile/tagsManage");
          } else {
            this.$toast.show(res.data.errorMsg, false);
          }
        });
      }
    },
    /**
     * 根据id查询分类
     */
    getTagById(id) {
      findTagById(id).then(res => {
        if (res.data.flag) {
          this.name = res.data.data.name;
          this.isUpdate = true;
        } else {
          this.$toast.show(res.data.errorMsg, false);
        }
      });
    },
    /**
     * 更改分类名
     */
    tagUpdate() {
      let validate = $(".ui.form").form("validate form");
      if (validate) {
        const tag = {
          id: this.id,
          name: this.name
        };
        updateTag(tag).then(res => {
          if (res.data.flag) {
            this.$toast.show("更改成功");
            this.$router.push("/profile/tagsManage");
          } else {
            this.$toast.show(res.data.errorMsg, false);
          }
        });
      }
    }
  },
  mounted() {
    if (this.$route.params.id) {
      this.id = this.$route.params.id;
      this.isUpdate = true;
      this.name = this.getTagById(this.id);
    }
    this.$nextTick(() => {
      $(".ui.form").form({
        fields: {
          title: {
            identifier: "name",
            rules: [
              {
                type: "empty",
                prompt: "标签: 请输入标签名称"
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