import {
  request
} from "./request"

// 根据博客Id查询博客详情信息
export function findBlogDetail(id) {
  return request({
    url: "/detail/findBlogDetail",
    params: {
      id
    }
  })
}