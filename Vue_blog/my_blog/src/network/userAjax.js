import {
  request
} from "./request"

// 获取管理员信息
export function findAdmin() {
  return request({
    url: "/user/findAdmin"
  })
}

// 获取用户信息
export function findUserMsg() {
  return request({
    url: "/user/findUserMsg"
  })
}