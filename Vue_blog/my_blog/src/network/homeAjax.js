import {
  request
} from "./request"

// 获取首页博客列表
export function findBlogList(currentPage = 1, pageSize = 10) {
  return request({
    url: "/home/findBlogList",
    params: {
      currentPage,
      pageSize
    }
  })
}

// 查询首页分类列表博客最多前六条
export function findTypeList() {
  return request({
    url: "/home/findTypeList"
  })
}

// 查询首页标签列表博客最多前十条
export function findTagList() {
  return request({
    url: "/home/findTagList"
  })
}

// 查询首页最新推荐博客列表前六条
export function findRecommendList() {
  return request({
    url: "/home/findRecommendList"
  })
}

// 查询博客搜索结果
export function findSearchBlogList(currentPage = 1, pageSize = 10, search = "") {
  return request({
    url: "/home/findSearchBlogList",
    params: {
      currentPage,
      pageSize,
      search
    }
  })
}