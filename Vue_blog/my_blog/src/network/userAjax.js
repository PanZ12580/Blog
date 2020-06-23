import {
  request
} from "./request"

// 获取管理员信息
export function findAdmin() {
  return request({
    url: "/user/findAdmin"
  })
}