import {
  request
} from "./request"

// 保存评论
export function saveComment(comment) {
  return request({
    url: "/comment/saveComment",
    method: "PUT",
    contentType: "application/json",
    data: {
      ...comment,
    }
  })
}

// 通过博客Id查询博客下的评论列表
export function findCommentListByBlogId(blogId) {
  return request({
    url: "/comment/findCommentListByBlogId",
    params: {
      blogId
    }
  })
}