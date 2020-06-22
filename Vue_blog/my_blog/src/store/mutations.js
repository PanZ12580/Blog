import {
  SET_USER,
  SET_LOADING,
  SET_SEARCH
} from './mutation_types'

export default {
  [SET_USER](state, payload) {
    state.user = payload.user
  },
  [SET_LOADING](state, payload) {
    state.loading = payload
  },
  [SET_SEARCH](state, payload) {
    state.search = payload
  }
}