package com.hzhang.service.impl;

import com.hzhang.pojo.UVStatistic;
import com.hzhang.service.UVStatisticService;
import com.hzhang.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/29 12:36
 * @description：网站访客统计业务逻辑层实现
 * @modified By：
 * @version: $
 */
@Service
public class UVStatisticServiceImpl implements UVStatisticService {
    @Autowired
    private RedisUtil redisUtil;

    private static final String PREFIX = "uniqueViews::";

    private Calendar calendar = Calendar.getInstance();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UVStatistic getStatistic(Integer recent) {
        calendar.setTime(new Date());
        LinkedHashMap<String, Long> statisticMap = new LinkedHashMap<>(recent);
        for (int i = recent - 1; i >= 0; i--) {
            String date = null;
            if (i == recent - 1) {
                date = getPastDate(i);
            } else {
                date = getPreDate(1);
            }
            String key = PREFIX + date;
            if (redisUtil.hasKey(key)) {
                statisticMap.put(date, redisUtil.pfcount(key));
            } else {
                statisticMap.put(date, 0L);
            }
        }
        UVStatistic uvStatistic = new UVStatistic();
        logger.info("访问量统计时间段： {}", statisticMap);
        uvStatistic.setStatisticMap(statisticMap);
        uvStatistic.setTotal(getTotal());
        return uvStatistic;
    }

    @Override
    public Long getTotal() {
        String[] keys = redisUtil.keys(PREFIX + "*");
        Long count = 0L;
        for (String k : keys) {
            count += redisUtil.pfcount(k);
        }
        return count;
    }

    public String getPastDate(Integer past) {
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(today);
    }

    public String getPreDate(Integer pre) {
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + pre);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(today);
    }
}
