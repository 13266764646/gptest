package com.gwy.aop;

import com.gwy.annotation.LogTraceId;
import com.gwy.utils.IdGenerator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.slf4j.MDC;

/**
 * @author
 * @since 2017/10/20
 */
@Aspect
@Order(1)
@Component
public class LogTraceIdAspect {


    @Before(value = "@annotation(log)")
    public void doAround(LogTraceId log) {
       MDC.put("X-B3-TraceId", IdGenerator.getUUIDStr());
//        System.out.println("======LogTraceIdAspect====doAround====");
    }


}
