import {
  SET_ADMIN,
  SET_LAST_BLOG_LIST
} from "./mutation_types"

import {
  findAdmin
} from "network/userAjax"
import {
  findBlogList
} from "network/homeAjax"

export default {
  /**
   * 获取管理员信息
   */
  getAdmin(context, payload) {
    findAdmin().then(res => {
      context.commit(SET_ADMIN, res.data.data);
    })
  },
  /**
   * 获取最新发布的至少四条博客
   */
  getLastBlogList(context, payload) {
    findBlogList().then(res => {
      let list = res.data.data.list
      list.splice(4)
      context.commit(SET_LAST_BLOG_LIST, list);
    })
  }
}