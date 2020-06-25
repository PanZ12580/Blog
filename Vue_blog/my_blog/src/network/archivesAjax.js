import {
  request
} from "./request"

// 获取归档页面博客时间分组映射
export function findArchivesBlogList() {
  return request({
    url: "/archives/findArchivesBlogList"
  })
}