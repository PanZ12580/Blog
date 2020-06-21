import axios from 'axios'
import router from '../router'
import store from "../store"
import {
  SET_LOADING
} from "../store/mutation_types"

// 后台管理页面的请求
export function profileRequest(config) {
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8001/profile',
    timeout: 5000
  })

  let timer = null
  // 请求拦截器
  instance.interceptors.request.use(config => {
    // 显示加载动画
    timer = setTimeout(() => {
      store.commit(SET_LOADING, true)
    }, 500)
    // 判断token是否存在，存在则设置为请求头
    if (localStorage.token) {
      config.headers.token = localStorage.token;
    }
    return config;
  }, err => {
    console.log(err)
    store.commit(SET_LOADING, false)
    router.replace('/500')
    return err;
  })


  // 响应拦截器
  instance.interceptors.response.use(res => {
    // 隐藏加载动画
    clearTimeout(timer)
    store.commit(SET_LOADING, false)
    if (res.data.statusCode === 500) {
      store.commit(SET_LOADING, false)
      router.replace('/500')
    } else if (res.data.statusCode === 404) {
      store.commit(SET_LOADING, false)
      router.replace('/404')
    } else if (res.data.statusCode === 401) {
      store.commit(SET_LOADING, false)
      router.replace('/401')
    }
    return res
  }, err => {
    store.commit(SET_LOADING, false)
    console.log(err)
    router.replace('/500')
    return err
  })

  return instance(config)
}

// 前台展示界面的请求
export function request(config) {
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8001/',
    timeout: 5000
  })

  // 响应拦截器
  instance.interceptors.response.use(res => {
    // 判断是否出现服务器错误
    if (res.data.statusCode == 500) {
      router.replace('/500')
    } else if (res.data.statusCode == 404) {
      router.replace('/404')
    }
    return res
  }, err => {
    console.log(err)
    router.replace('/500')
  })

  return instance(config)
}