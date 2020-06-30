import {
  request
} from "./request"

// 网站独立访客统计控制器
export function findStatistic(recent) {
  return request({
    url: "/statistic/findStatistic",
    params: {
      recent
    }
  })
}