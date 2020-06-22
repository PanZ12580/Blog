import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "../store"

import {
  findUserByToken
} from "network/profileAjax"
import {
  SET_USER
} from "store/mutation_types";

Vue.use(VueRouter)
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
};

const routes = [{
    path: '',
    redirect: '/home'
  },
  {
    path: '/home',
    component: () => import('views/home/Home'),
    meta: {
      title: "首页"
    }
  },
  {
    path: '/detail',
    component: () => import('views/detail/Detail'),
    meta: {
      title: "博客详细"
    }
  },
  {
    path: '/categories',
    component: () => import('views/categories/Categories'),
    meta: {
      title: "分类"
    }
  },
  {
    path: "/search",
    component: () => import('views/search/Search'),
    meta: {
      title: "搜索结果"
    }
  },
  {
    path: '/profile',
    component: () => import('views/profile/Profile'),
    children: [{
        path: '',
        name: 'Profile',
        component: () => import('views/profile/home/ProfileHome'),
        meta: {
          requireAuth: true,
          title: "个人中心"
        }
      },
      // ----------------------登录------------------------------
      {
        path: 'login',
        component: () => import('views/profile/login/Login'),
        meta: {
          title: "个人中心登录",
          requireAuth: false
        }
      },
      // ---------------------博客管理--------------------------------
      {
        path: 'blogPublish',
        component: () => import('views/profile/publish/BlogPublish'),
        meta: {
          requireAuth: true,
          title: "博客发布"
        }
      },
      {
        path: 'blogEdit/:id',
        component: () => import('views/profile/publish/BlogPublish'),
        meta: {
          requireAuth: true,
          title: "博客编辑"
        }
      },
      {
        path: 'blogManage',
        component: () => import('views/profile/blogManage/BlogManage'),
        meta: {
          requireAuth: true,
          title: "博客管理列表"
        }
      },
      // ----------------------分类管理------------------------------
      {
        path: 'typesManage',
        component: () => import('views/profile/blogManage/TypesManage'),
        meta: {
          requireAuth: true,
          title: "博客分类管理"
        }
      },
      {
        path: 'typePublish',
        component: () => import('views/profile/publish/TypePublish'),
        meta: {
          requireAuth: true,
          title: "分类名称添加"
        }
      },
      {
        path: 'typeEdit/:id',
        component: () => import('views/profile/publish/TypePublish'),
        meta: {
          requireAuth: true,
          title: "分类名称修改"
        }
      },
      // ----------------------标签管理------------------------------
      {
        path: 'tagsManage',
        component: () => import('views/profile/blogManage/TagsManage'),
        meta: {
          requireAuth: true,
          title: "博客标签管理"
        }
      },
      {
        path: 'tagPublish',
        component: () => import('views/profile/publish/TagPublish'),
        meta: {
          requireAuth: true,
          title: "标签名称添加"
        }
      },
      {
        path: 'tagEdit/:id',
        component: () => import('views/profile/publish/TagPublish'),
        meta: {
          requireAuth: true,
          title: "标签名称修改"
        }
      }
    ]
  },
  {
    path: '/401',
    component: () => import('views/error/401'),
    meta: {
      title: '401'
    }
  },
  {
    path: '/500',
    component: () => import('views/error/500'),
    meta: {
      title: '500'
    }
  },
  {
    path: '*',
    component: () => import('views/error/404'),
    meta: {
      title: '404'
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  document.title = to.meta.title
  if (to.meta.requireAuth) {
    if (localStorage.token) {
      // 如果store中已有用户信息则放行
      if (store.getters.user) {
        next();
      }
      // 通过token(请求头中)获取用户信息
      findUserByToken().then(res => {
        if (res.data.flag) {
          store.commit(SET_USER, {
            user: res.data.data
          });
          next();
        } else {
          localStorage.removeItem("token")
          next({
            path: '/profile/login'
          });
        }
      }).catch(err => {
        router.push("/500")
      })
    } else {
      next({
        path: '/profile/login'
      });
    }
  } else {
    next();
  }
})

export default router