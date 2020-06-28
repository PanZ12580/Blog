package com.hzhang.aspect;

import com.hzhang.annotation.ClearRedisCache;
import com.hzhang.utils.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/1 22:00
 * @description：调用Service层方法前清空缓存
 * @modified By：
 * @version: $
 */
@Component
@Aspect
public class ClearCacheAspect {
    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("execution(* com.hzhang.service.*.*(..))")
    public void clearCache() {
    }

    @Before("clearCache()")
    public void doBefore(JoinPoint joinPoint) {
//        找到被调用的方法并判断是否加上了清空缓存的注解
        Class targetClass = joinPoint.getTarget().getClass();
        boolean isClear = false;
        Method[] methods = targetClass.getMethods();
        Class<?>[] cascade = null;
        for (Method method : methods) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                if (AnnotationUtils.findAnnotation(method, ClearRedisCache.class) != null) {
                    ClearRedisCache annotation = AnnotationUtils.findAnnotation(method, ClearRedisCache.class);
                    cascade = annotation.cascade();
                    isClear = true;
                    break;
                }
            }
        }
//        如果需要清空缓存
        if (isClear) {
//            获取key
            String[] key = {"cache::" + joinPoint.getSignature().getDeclaringTypeName() + "*"};
//            需要级联删除的key
            String[] cascadeKeys = Arrays.stream(cascade)
                    .map(cls -> "cache::" + cls.getName() + "*")
                    .toArray(len -> new String[len]);
//            模糊删除对应的key
            Stream.of(key, cascadeKeys)
                    .flatMap(Arrays::stream)
                    .forEach(k -> {
                        String[] keys = redisUtil.keys(k);
                        if (keys != null && keys.length > 0) {
                            redisUtil.del(keys);
                        }
                    });
        }
    }
}
