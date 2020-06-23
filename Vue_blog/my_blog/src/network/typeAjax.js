import {
  request
} from "./request"

// 获取分类列表
export function findTypeList() {
  return request({
    url: "/type/findTypeList"
  })
}

// 通过分类Id查询博客
export function findBlogByTypeId(currentPage, pageSize, typeId) {
  return request({
    url: "/type/findBlogByTypeId",
    params: {
      currentPage,
      pageSize,
      typeId
    }
  })
}