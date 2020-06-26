import {
  request
} from "./request"

// 保存留言内容
export function saveMessage(message) {
  return request({
    url: "/message/saveMessage",
    method: "PUT",
    contentType: "application/json",
    data: {
      ...message,
    }
  })
}

// 查询留言列表
export function findMessageList() {
  return request({
    url: "/message/findMessageList"
  })
}