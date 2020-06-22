import Vue from 'vue'
import Vuex from 'vuex'

import mutations from './mutations'
import getters from './getters'

Vue.use(Vuex)

const state = {
  user: '',
  search: "",
  loading: false
}

export default new Vuex.Store({
  state: state,
  mutations: mutations,
  getters: getters,
  actions: {},
  modules: {}
})