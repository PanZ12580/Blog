import {
  musicRequest
} from "./request"

// 获取歌词
export function findLyric(id) {
  return musicRequest({
    url: "/lyric",
    params: {
      id
    }
  })
}

// 获取歌单
export function findPlayList() {
  return musicRequest({
    url: "/playlist/detail?id=138900787"
  })
}

// 获取歌曲详情（图片）
export function findSongDetail(ids) {
  return musicRequest({
    url: "/song/detail",
    params: {
      ids
    }
  })
}