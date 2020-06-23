import {
  request
} from "./request"

// 获取标签列表
export function findTagList() {
  return request({
    url: "/tag/findTagList"
  })
}

// 通过标签Id查询博客
export function findBlogByTagId(currentPage, pageSize, tagId) {
  return request({
    url: "/tag/findBlogByTagId",
    params: {
      currentPage,
      pageSize,
      tagId
    }
  })
}