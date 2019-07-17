package com.gwy.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 何种场景下的通用日志打印
     * @return
     */
    String value();
}
