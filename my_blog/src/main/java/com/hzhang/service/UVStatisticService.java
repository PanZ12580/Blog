package com.hzhang.service;

import com.hzhang.pojo.UVStatistic;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/29 12:15
 * @description：网站访客统计业务逻辑层
 * @modified By：
 * @version: $
 */
public interface UVStatisticService {
    /**
     * 获得最近recent天的访客统计数据
     * @param recent
     * @return
     */
    UVStatistic getStatistic(Integer recent);

    /**
     * 获得历史访客总计数量
     * @return
     */
    Long getTotal();
}
