package com.gwy.aop;

import com.gwy.annotation.Log;
import org.springframework.stereotype.Component;

@Component
public class LoggerApply {

    @Log(value = "http://www.cnblogs.com/lingyejun/")
    public void lingLong(String event)throws Exception {
        System.out.println("lingLogger(String event) : lingyejun will auth by blog address");
//        throw new Exception();
    }
}