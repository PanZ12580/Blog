<template>
  <div id="login_container" class="m-container-tiny">
    <div class="ui container">
      <div class="ui middle aligned center aligned grid">
        <div class="column">
          <h2 class="ui teal image header">
            <img src="~assets/logo.png" class="image" />
            <div class="content">个人中心登录</div>
          </h2>
          <form class="ui large form">
            <div class="ui segment">
              <div class="field">
                <div class="ui left icon input">
                  <i class="user teal icon"></i>
                  <input type="text" @keyup.enter="login" v-model="username" name="username" placeholder="请输入用户名" />
                </div>
              </div>
              <div class="field">
                <div class="ui left icon input">
                  <i class="lock teal icon"></i>
                  <input @keyup.enter="login" type="password" v-model="password" name="password" placeholder="请输入密码" />
                </div>
              </div>
              <div class="ui fluid large teal button" @click="login">登录</div>
            </div>
            <div class="ui error mini message"></div>
            <div class="ui negative mini message" v-if="errorMsg">{{errorMsg}}</div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { profileLogin } from "network/profileAjax";
import { SET_USER } from "store/mutation_types";

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
      errorMsg: ""
    };
  },
  mounted() {
    this.$nextTick(() => {
      $(".ui.form").form({
        fields: {
          username: {
            identifier: "username",
            rules: [
              {
                type: "empty",
                prompt: "请输入用户名"
              }
            ]
          },
          password: {
            identifier: "password",
            rules: [
              {
                type: "empty",
                prompt: "请输入密码"
              }
            ]
          }
        }
      });
    });
  },
  methods: {
    login() {
      let validate = $(".ui.form").form("validate form");
      if (validate) {
        profileLogin(this.username, this.password)
          .then(res => {
            if (res.data.flag) {
              window.localStorage["token"] = res.data.token;
              this.$store.commit(SET_USER, {
                user: res.data.data
              });
              this.$router.push("/profile");
            } else {
              this.errorMsg = res.data.errorMsg;
            }
          })
          .catch(err => {
            console.log(err);
            this.$router.push("/500");
          });
      }
    }
  }
};
</script>

<style scoped>
#login_container {
  margin-bottom: 20em !important;
  margin-top: 5em !important;
}
.field,
.submit {
  margin-bottom: 2em !important;
  margin-top: 2em !important;
}
</style>