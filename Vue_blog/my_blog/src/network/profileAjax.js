import {
  profileRequest
} from "./request"
import qs from "qs"

// ------------------------------------登录-----------------------------------------------
// 登录验证
export function profileLogin(username, password) {
  return profileRequest({
    url: '/login',
    method: 'POST',
    contentType: 'application/x-www-form-urlencoded',
    data: qs.stringify({
      username,
      password
    })
  })
}

// 根据请求头中的token获取用户信息
export function findUserByToken() {
  return profileRequest({
    url: '/findUserByToken',
    method: 'GET'
  })
}

// 注销
export function profileLogout() {
  return profileRequest({
    url: '/logout',
    method: 'GET'
  })
}

// ------------------------------------分类管理-----------------------------------------------
// 获取分类列表
export function findTypes(currentPage, pageSize) {
  return profileRequest({
    url: '/types',
    methods: 'GET',
    params: {
      currentPage,
      pageSize
    }
  })
}

// 增加分类
export function saveType(name) {
  return profileRequest({
    url: '/saveType',
    method: 'PUT',
    contentType: 'application/json',
    data: {
      name
    }
  })
}

// 根据id查找分类
export function findTypeById(id) {
  return profileRequest({
    url: '/findTypeById',
    method: 'GET',
    params: {
      id
    }
  })
}

// 根据type的id修改分类
export function updateType(type) {
  return profileRequest({
    url: '/updateType',
    method: 'PUT',
    contentType: 'application/json',
    data: type
  })
}

// 根据id删除分类
export function deleteType(id) {
  return profileRequest({
    url: '/deleteType',
    method: 'DELETE',
    params: {
      id
    }
  })
}

// ------------------------------------标签管理-----------------------------------------------
// 获取标签列表
export function findTags(currentPage, pageSize) {
  return profileRequest({
    url: '/tags',
    methods: 'GET',
    params: {
      currentPage,
      pageSize
    }
  })
}

// 增加标签
export function saveTag(name) {
  return profileRequest({
    url: '/saveTag',
    method: 'PUT',
    contentType: 'application/json',
    data: {
      name
    }
  })
}

// 根据id查找标签
export function findTagById(id) {
  return profileRequest({
    url: '/findTagById',
    method: 'GET',
    params: {
      id
    }
  })
}

// 根据type的id修改标签
export function updateTag(tag) {
  return profileRequest({
    url: '/updateTag',
    method: 'PUT',
    contentType: 'application/json',
    data: tag
  })
}

// 根据id删除标签
export function deleteTag(id) {
  return profileRequest({
    url: '/deleteTag',
    method: 'DELETE',
    params: {
      id
    }
  })
}

// ------------------------------------博客管理-----------------------------------------------
// 获取博客列表
export function findBlogList(blogManageQueryVo) {
  return profileRequest({
    url: "/findBlogList",
    method: "POST",
    contentType: "application/json",
    data: {
      ...blogManageQueryVo
    }
  })
}

// 根据id查找博客
export function findBlogById(id) {
  return profileRequest({
    url: "/findBlogById",
    method: 'GET',
    params: {
      id
    }
  })
}

// 保存博客
export function saveBlog(blog) {
  return profileRequest({
    url: "/saveBlog",
    method: 'PUT',
    contentType: 'application/json',
    data: {
      ...blog
    }
  })
}

// 更新指定博客
export function updateBlog(blog) {
  return profileRequest({
    url: "/updateBlog",
    method: "PUT",
    data: {
      ...blog
    }
  })
}

// 根据id删除指定博客
export function deleteBlog(id) {
  return profileRequest({
    url: "/deleteBlog",
    method: 'DELETE',
    params: {
      id
    }
  })
}

// ------------------------------------友链管理-----------------------------------------------
// 查询所有的友情链接
export function findLinkList() {
  return profileRequest({
    url: "/findLinkList"
  })
}

// 更新友情链接（设置允许状态）
export function updateLink(link) {
  return profileRequest({
    url: "/updateLink",
    method: 'PUT',
    contentType: 'application/json',
    data: {
      ...link
    }
  })
}

// 删除友情链接
export function deleteLink(id) {
  return profileRequest({
    url: "/deleteLink",
    method: 'DELETE',
    params: {
      id
    }
  })
}