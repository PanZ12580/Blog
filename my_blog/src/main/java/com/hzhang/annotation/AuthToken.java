package com.hzhang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/1 20:45
 * @description：标识需要Token验证的接口
 * @modified By：
 * @version: $
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthToken {

}
