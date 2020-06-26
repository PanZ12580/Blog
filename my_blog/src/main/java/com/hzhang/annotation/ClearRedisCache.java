package com.hzhang.annotation;

import java.lang.annotation.*;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/1 22:13
 * @description：调用方法前清空Redis中对应的缓存
 * @modified By：
 * @version: $
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClearRedisCache {
    /**
     * 需要级联删除的缓存
     * @return
     */
    Class<?>[] cascade() default {};
}
