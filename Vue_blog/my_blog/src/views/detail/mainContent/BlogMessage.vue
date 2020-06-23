<template>
  <!--        博客信息-->
  <div class="ui message attached info m-bg">
    <div class="ui grid middle aligned">
      <div class="ui eleven wide column">
        <ul class="list">
          <li>作者：{{user.nickname}}（联系作者）</li>
          <li>发表时间：{{fCreateTime}}</li>
          <li>最后更新时间：{{fUpdateTime}}</li>
          <li>版权声明：自由转载-非商用-非衍生-保持署名（创意共享3.0许可）</li>
          <li>转载：请在文末添加作者原文链接</li>
        </ul>
      </div>
      <div class="ui five wide column">
        <img
          src="~assets/images/wechat.jpg"
          class="ui image rounded bordered right floated"
          alt
          style="width: 120px;"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { formatDate } from "common/utils";

export default {
  name: "BlogMessage",
  props: {
    user: {
      type: Object,
      default() {
        return {};
      }
    },
    createTime: "",
    updateTime: "",
  },
  computed: {
    fCreateTime() {
      return formatDate(new Date(this.createTime), "yyyy-MM-dd hh:mm:ss");
    },
    fUpdateTime() {
      return formatDate(new Date(this.updateTime), "yyyy-MM-dd hh:mm:ss")
    }
  }
};
</script>

<style>
</style>