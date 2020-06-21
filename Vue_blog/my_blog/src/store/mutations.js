import {
  SET_USER,
  SET_LOADING
} from './mutation_types'

export default {
  [SET_USER](state, payload) {
    state.user = payload.user
  },
  [SET_LOADING](state, payload) {
    state.loading = payload
  }
}