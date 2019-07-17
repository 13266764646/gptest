package com.gwy.annotation;

import com.gwy.aop.LogTraceIdAspect;

import java.lang.annotation.*;

/**
 * 日志加上UUID,适用于MQ，自启线程
 * {@link LogTraceIdAspect}
 *
 * @author Haixiang.Zheng
 * @since 2017/11/15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogTraceId {

}
