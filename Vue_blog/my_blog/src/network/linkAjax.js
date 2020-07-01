import {
  request
} from "./request"

// 查询所有已允许的友情链接
export function findAllowedLinkList() {
  return request({
    url: "/link/findAllowedLinkList"
  })
}

// 上传友情链接
export function saveLink(link) {
  return request({
    url: "/link/saveLink",
    method: 'PUT',
    contentType: 'application/json',
    data: {
      ...link
    }
  })
}