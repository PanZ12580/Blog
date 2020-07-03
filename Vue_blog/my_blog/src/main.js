import App from './App.vue'
import router from './router'
import store from './store'

import VueLazyLoad from 'vue-lazyload'
import toast from "components/toast"

Vue.config.productionTip = false
Vue.prototype.$bus = new Vue();

// 安装自定义toast插件
Vue.use(toast)

// 解决移动端300ms延迟问题
FastClick.attach(document.body)
// 图片懒加载
Vue.use(VueLazyLoad, {
  loading: 'http://cdn.panzvor.com/placeholder.png'
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')