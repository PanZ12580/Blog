
import mutations from './mutations'
import getters from './getters'
import actions from './actions'

Vue.use(Vuex)

const state = {
  user: '',
  search: "",
  admin: "",
  musicStart: false,
  lastBlogList: [],
  loading: false
}

export default new Vuex.Store({
  state: state,
  mutations: mutations,
  getters: getters,
  actions: actions,
  modules: {}
})