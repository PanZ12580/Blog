<template>
  <!--中间内容-->
  <div id="main" class="m-padding-tb-max m-container-medium">
    <div class="ui container">
      <form class="ui form" :class="{loading: isLoading}">
        <!-- 标记 -->
        <div class="field required">
          <div class="ui left labeled input">
            <div class="label ui selection compact teal basic dropdown">
              <i class="dropdown icon"></i>
              <div class="text" v-text="this.blog.flag? this.blog.flag : flag[0]"></div>
              <div class="menu">
                <div
                  class="item m-gray"
                  @click="changeFlag(item)"
                  v-for="(item, index) in flag"
                  :key="index"
                >{{item}}</div>
              </div>
            </div>
            <input
              v-model="blog.title"
              name="title"
              type="text"
              placeholder="标题"
              :class="{error: error.title && !blog.title}"
            />
          </div>
        </div>
        <!-- 编辑区 -->
        <div class="required field" :class="{error: error.content && !blog.content}">
          <mavon-editor @fullScreen="fullScreen" id="editor" v-model="blog.content" />
        </div>
        <!-- 分类与标签 -->
        <div class="two fields">
          <!-- 分类选择 -->
          <div class="field">
            <div
              class="left labeled ui action input"
              :class="{error: error.type && blogType === ''}"
            >
              <label class="ui compact teal basic label">分类</label>
              <div class="ui fluid selection dropdown">
                <i class="icon dropdown"></i>
                <div class="default text" v-if="blogType === ''">请选择分类</div>
                <div class="text" v-else v-text="blogType"></div>
                <div class="menu">
                  <div
                    class="item"
                    v-for="(item, index) in typeList"
                    :key="index"
                    v-text="item.name"
                    @click="selectType(item)"
                  ></div>
                </div>
              </div>
            </div>
          </div>
          <!--  标签选择-->
          <div class="field tag-field">
            <label class="ui compact teal basic label tag-tip">标签</label>
            <select
              ref="select"
              multiple
              class="ui dropdown search tag-select"
              v-model="blog.tagIds"
            >
              <option disabled value>请选择标签</option>
              <option
                v-for="(item, index) in tagList"
                :key="index"
                :value="item.id"
                v-text="item.name"
              ></option>
            </select>
          </div>
        </div>
        <!-- 首图 -->
        <div class="field">
          <div class="ui left labeled input">
            <div class="labeled left ui input">
              <label class="ui compact teal basic label">首图</label>
              <input type="text" v-model="blog.firstPicture" placeholder="首图引用地址" />
            </div>
          </div>
        </div>
        <!-- 博客描述 -->
        <div class="field">
          <textarea
            name="description"
            id="description"
            placeholder="博客描述..."
            v-model="blog.description"
            :class="{error: error.description && !blog.description}"
          ></textarea>
        </div>
        <!-- checkBox -->
        <div class="inline fields">
          <div class="field">
            <div class="ui checkbox">
              <input
                type="checkbox"
                v-model="blog.recommend"
                id="recommend"
                name="recommend"
                checked
                class="hidden"
              />
              <label for="recommend">推荐</label>
            </div>
          </div>
          <div class="field">
            <div class="ui checkbox">
              <input
                type="checkbox"
                v-model="blog.shareStatement"
                id="shareInfo"
                name="shareInfo"
                class="hidden"
              />
              <label for="shareInfo">转载声明</label>
            </div>
          </div>
          <div class="field">
            <div class="ui checkbox">
              <input
                type="checkbox"
                id="appreciation"
                v-model="blog.appreciation"
                name="recommend"
                class="hidden"
              />
              <label for="appreciation">赞赏</label>
            </div>
          </div>
          <div class="field">
            <div class="ui checkbox">
              <input
                type="checkbox"
                id="comment"
                v-model="blog.commentabled"
                name="comment"
                class="hidden"
              />
              <label for="comment">评论</label>
            </div>
          </div>
        </div>
        <!-- 操作按钮 -->
        <div class="container ui right aligned">
          <div class="ui button" onclick="window.history.go(-1)">返回</div>
          <div @click="publish('save')" class="ui secondary button">保存</div>
          <div @click="publish('update')" class="ui teal button" v-if="isUpdate">更新</div>
          <div @click="publish('publish')" class="ui teal button" v-else>发布</div>
        </div>
        <!-- 错误信息 -->
        <success-msg :isShow="errorShow" :msgList="errorList" msgClass="negative"></success-msg>
      </form>
    </div>
  </div>
</template>

<script>
import MavonEditor from "mavon-editor";
import "mavon-editor/dist/css/index.css";
import Vue from "vue";

Vue.use(MavonEditor);
import {
  findBlogById,
  findTypes,
  findTags,
  updateBlog,
  saveBlog
} from "network/profileAjax";

export default {
  name: "BlogPublish",
  data() {
    return {
      isLoading: false,
      isUpdate: false,
      errorShow: false,
      error: {
        title: false,
        content: false,
        type: false,
        description: false
      },
      errorList: [],
      flag: ["原创", "转载", "翻译"],
      content: "",
      blogId: {
        type: Number,
        default: null
      },
      blog: {
        content: "",
        flag: "原创",
        type: {},
        tagList: [],
        tagIds: [],
        firstPicture: "",
        appreciation: true,
        shareStatement: true,
        commentabled: true,
        recommend: true
      },
      typeList: {
        type: Array,
        default() {
          return [];
        }
      },
      tagList: []
    };
  },
  components: {
    "success-msg": () => import("../msg/SuccessMsg")
  },
  watch: {
    $route() {
      if (this.$route.params.id != null && this.$route.params.id != undefined) {
        this.blogId = this.$route.params.id;
        this.isUpdate = true;
        this.getBlog();
      } else {
        Object.assign(this.$data, this.$options.data());
        this.getTypes();
        this.getTags();
        let tags = document.querySelectorAll(".tag-select a.ui.label");
        for (let tag of tags) {
          tag.parentElement.removeChild(tag);
        }
        let selectedTag = document.querySelectorAll(
          ".tag-select .item.active.filtered"
        );
        for (let tag of selectedTag) {
          tag.className = "item selected";
        }
      }
    }
  },
  mounted() {
    // 控件事件
    this.$nextTick(() => {
      $(".ui.dropdown").dropdown({
        on: "click"
      });
    });
    if (this.$route.params.id != null && this.$route.params.id != undefined) {
      this.blogId = this.$route.params.id;
      this.isUpdate = true;
      this.getBlog();
    }
    this.getTypes();
    this.getTags();
  },
  computed: {
    blogType() {
      return this.blog.type && Object.keys(this.blog.type).length !== 0
        ? this.blog.type.name
        : "";
    }
  },
  methods: {
    /**
     * 根据id获取博客
     */
    getBlog() {
      findBlogById(this.blogId).then(res => {
        this.blog = res.data.data;
      });
    },
    /**
     * 获取分类
     */
    getTypes() {
      findTypes(1, 0).then(res => {
        this.typeList = res.data.data.list;
      });
    },
    /**
     * 选择分类
     */
    selectType(type) {
      this.$nextTick(() => {
        this.blog.type = type;
      });
    },
    /**
     * 获取标签列表
     */
    getTags() {
      findTags(1, 0).then(res => {
        this.tagList = res.data.data.list;
      });
    },
    /**
     * 更改博客标记
     */
    changeFlag(item) {
      this.blog.flag = item;
    },
    /**
     * 编辑区全屏
     */
    fullScreen(status, value) {
      this.$bus.$emit("hideNav", status);
    },
    /**
     * 表单校验
     */
    validate() {
      this.errorList = [];
      this.error = {
        title: false,
        content: false,
        type: false,
        description: false
      };
      if (!this.blog.title) {
        this.errorList.push("标题：请输入标题");
        this.errorShow = true;
        this.error.title = true;
      }
      if (!this.blog.content) {
        this.errorList.push("内容：请输入内容");
        this.errorShow = true;
        this.error.content = true;
      }
      if (Object.keys(this.blog.type).length === 0) {
        this.errorList.push("分类：请输入分类");
        this.errorShow = true;
        this.error.type = true;
      }
      if (!this.blog.description) {
        this.errorList.push("描述：请输入博客描述");
        this.errorShow = true;
        this.error.description = true;
      }
      return (
        this.blog.title &&
        this.blog.content &&
        this.blog.type &&
        this.blog.description
      );
    },
    /**
     * 表单提交前更新博客的标签列表与用户id
     */
    updateTagAndUser() {
      this.blog.tagList = [];
      this.blog.tagList = this.tagList.filter(
        item => this.blog.tagIds.indexOf(item.id) != -1
      );
      this.blog.user = this.$store.getters.user;
    },
    /**
     * 发布博客
     */
    publish(opt) {
      let validate = this.validate();
      if (validate) {
        this.isLoading = true;
        this.updateTagAndUser();
        switch (opt) {
          case "save":
            this.blog.published = false;
            if (this.$route.path.indexOf("blogEdit") !== -1) {
              updateBlog(this.blog).then(res => {
                this.isLoading = false;
                this.$toast.show("保存成功！");
                this.$router.push("/profile/blogManage");
              });
            } else {
              saveBlog(this.blog).then(res => {
                this.isLoading = false;
                this.$toast.show("保存成功！");
                this.$router.push("/profile/blogManage");
              });
            }
            break;
          case "update":
            this.blog.published = true;
            updateBlog(this.blog).then(res => {
              this.isLoading = false;
              this.$toast.show("更新成功！");
              this.$router.push("/profile/blogManage");
            });
            break;
          case "publish":
            this.blog.published = true;
            saveBlog(this.blog).then(res => {
              this.isLoading = false;
              this.$toast.show("发布成功！");
              this.$router.push("/profile/blogManage");
            });
            break;
        }
        this.errorShow = false;
      }
    }
  }
};
</script>

<style>
#editor {
  margin: auto;
  z-index: 9;
  min-height: 600px;
}
.ui.form textarea:not([rows]) {
  height: inherit;
  max-height: inherit;
}
.v-note-wrapper .v-note-panel > div {
  max-height: 700px;
}
.v-note-wrapper .v-note-panel {
  overflow: auto;
}
.error {
  background-color: #fff1f1 !important;
  border: 1px solid #ffa4a4 !important;
}
.tag-field {
  display: flex;
}
.tag-tip {
  flex: 0 0 auto;
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  text-transform: none !important;
  font-weight: 700 !important;
  margin: 0 !important;
  font-size: 1em !important;
  line-height: 1;
  vertical-align: baseline;
  padding-top: 0.78571429em;
  padding-bottom: 0.78571429em;
}
.ui.dropdown.selection.multiple {
  flex: 1;
  margin-left: 0;
}
</style>