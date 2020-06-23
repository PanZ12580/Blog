import {
  SET_USER,
  SET_LOADING,
  SET_SEARCH,
  SET_ADMIN,
  SET_LAST_BLOG_LIST,
  SET_MUSIC_STATUS
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
  },
  [SET_ADMIN](state, payload) {
    state.admin = payload
  },
  [SET_LAST_BLOG_LIST](state, payload) {
    state.lastBlogList = payload;
  },
  [SET_MUSIC_STATUS](state, payload) {
    state.musicStart = payload;
  }
}