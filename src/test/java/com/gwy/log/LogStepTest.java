package com.gwy.log;

import com.gwy.utils.LoggerStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogStepTest {

    private final static Logger logger = LoggerFactory.getLogger(LogStepTest.class);

    public static void main(String[] args) {
        LoggerStep loggerStep = LoggerStep.build(logger, 3);
        loggerStep.start();

        loggerStep.log("调用sku服务");
        logger.info("=================调用sku服务==================");
        loggerStep.logOk();

        loggerStep.log("调用spu服务");
        logger.info("=================调用spu服务==================");
        loggerStep.logOk();

        loggerStep.log("调用优惠券服务");
        logger.info("=================调用优惠券服务==================");
        loggerStep.logOk();

        loggerStep.end();
    }

//10:02:59.963 [main] INFO com.gwy.log.LogStepTest - [log stage begin]
//            10:02:59.975 [main] INFO com.gwy.log.LogStepTest - step 1/3 : 调用sku服务
//10:02:59.975 [main] INFO com.gwy.log.LogStepTest - =================调用sku服务==================
//            10:02:59.976 [main] INFO com.gwy.log.LogStepTest - step 1/3 : ok
//10:02:59.976 [main] INFO com.gwy.log.LogStepTest - step 2/3 : 调用spu服务
//10:02:59.976 [main] INFO com.gwy.log.LogStepTest - =================调用spu服务==================
//            10:02:59.976 [main] INFO com.gwy.log.LogStepTest - step 2/3 : ok
//10:02:59.977 [main] INFO com.gwy.log.LogStepTest - step 3/3 : 调用优惠券服务
//10:02:59.977 [main] INFO com.gwy.log.LogStepTest - =================调用优惠券服务==================
//            10:02:59.978 [main] INFO com.gwy.log.LogStepTest - step 3/3 : ok
//10:02:59.978 [main] INFO com.gwy.log.LogStepTest - [log successfully.]
}