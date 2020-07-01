<template>
  <div>
    <div class="button link red ui" @click="apply">
      <i class="icon heart"></i>
      友链申请
    </div>
    <div class="ui modal apply">
      <i class="close icon"></i>
      <div class="header">友情链接申请</div>
      <div class="content">
        <div class="ui grid stackable">
          <div class="two column row center aligned">
            <div class="column">
              <h4 class="header ui dividing">申请</h4>
              <form class="ui form">
                <div class="field left-label">
                  <label for="url">网站地址</label>
                  <input type="text" id="url" name="url" v-model="url" placeholder="网站地址..." />
                </div>
                <div class="field left-label">
                  <label for="name">网站名称</label>
                  <input type="text" id="name" name="name" v-model="name" placeholder="网站名称..." />
                </div>
                <div class="field left-label">
                  <label for="picture">图片链接</label>
                  <input
                    type="text"
                    id="picture"
                    name="picture"
                    v-model="picture"
                    placeholder="图片链接地址..."
                  />
                </div>
                <div class="ui error message"></div>
              </form>
            </div>
            <div class="column">
              <h4 class="header ui dividing">预览</h4>
              <div class="ui raised card centered">
                <a class="image" :href="url" target="_blank">
                  <img v-if="!picture" src="http://cdn.panzvor.com/placeholder.png" alt />
                  <img v-else :src="picture" />
                </a>
                <div class="content">
                  <a class="header center aligned" v-text="name" :href="url" target="_blank"></a>
                </div>
                <div class="extra content center aligned">
                  <span v-text="date"></span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="actions">
        <div class="ui button" @click="reset">重置</div>
        <div class="ui green button" @click="submit">提交申请</div>
      </div>
    </div>
  </div>
</template>

<script>
import { formatDate } from "common/utils";
import { saveLink } from "network/linkAjax";

export default {
  name: "Apply",
  data() {
    return {
      url: "",
      name: "",
      picture: ""
    };
  },
  computed: {
    date() {
      return formatDate(new Date(), "yyyy-MM-dd");
    }
  },
  mounted() {
    // 表单校验规则
    $(".ui.form").form({
      fields: {
        url: {
          identifier: "url",
          rules: [
            {
              type: "url",
              prompt: "请输入正确的网址"
            }
          ]
        },
        name: {
          identifier: "name",
          rules: [
            {
              type: "empty",
              prompt: "请输入站点名称"
            }
          ]
        },
        picture: {
          identifier: "picture",
          rules: [
            {
              type: "url",
              prompt: "请输入正确的图片链接"
            }
          ]
        }
      }
    });
  },
  methods: {
    /**
     * 申请模态框
     */
    apply() {
      $(".ui.modal.apply").modal("show");
    },
    /**
     * 表单提交
     */
    submit() {
      let validate = $(".ui.form").form("validate form");
      if (validate) {
        let link = {
          url: this.url,
          name: this.name,
          picture: this.picture
        };
        saveLink(link)
          .then(res => {
            if (res.data.flag) {
              this.$toast.show("申请成功");
            } else {
              this.$toast.show("申请失败" + res.data.errorMsg, false);
            }
          })
          .catch(err => {
            this.$toast.show("申请失败" + err, false);
          });
      }
    },
    /**
     * 表单重置
     */
    reset() {
      Object.assign(this.$data, this.$options.data());
    }
  }
};
</script>

<style scoped>
.left-label {
  text-align: left !important;
}
.modal {
  margin: 0 !important;
}
</style>