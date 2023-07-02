package com.wgp.mall.common.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 日志注解
 * 用来打印入参、出参
 *
 * @author gangpeng.wgp
 * @date 2023/7/2 下午2:31
 */
@Retention(RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Log {

    boolean logInput() default true;

    boolean logOutput() default true;


}
