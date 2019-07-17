package com.gwy.aop;

import com.alibaba.fastjson.JSON;
import com.gwy.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Order(1)
@Component
@Lazy(false)
//https://www.cnblogs.com/lingyejun/p/9941350.html
public class LoggerAspect {
    private  final static Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    private final String POINT_CUT = "execution(* com.gwy.service..*.*(..))";

    /**
     * 定义切入点：对要拦截的方法进行定义与限制，如包、类
     * 1、execution(public * *(..)) 任意的公共方法
     * 2、execution（* set*（..）） 以set开头的所有的方法
     * 3、execution（* com.lingyejun.annotation.LoggerApply.*（..））com.lingyejun.annotation.LoggerApply这个类里的所有的方法
     * 4、execution（* com.lingyejun.annotation.*.*（..））com.lingyejun.annotation包下的所有的类的所有的方法
     * 5、execution（* com.lingyejun.annotation..*.*（..））com.lingyejun.annotation包及子包下所有的类的所有的方法
     * 6、execution(* com.lingyejun.annotation..*.*(String,?,Long)) com.lingyejun.annotation包及子包下所有的类的有三个参数，第一个参数为String类型，第二个参数为任意类型，第三个参数为Long类型的方法
     * 7、execution(@annotation(com.lingyejun.annotation.Lingyejun))

     */
    //查看文档https://blog.csdn.net/LuQiaoYa/article/details/88233846
//    @Pointcut("@annotation(com.gwy.annotation.Log)")
    @Pointcut(POINT_CUT)
    private void cutMethod(){
        logger.info("===============cutMethod===========");
    }

    @Before(value = "cutMethod()")
    public void begin(){
        logger.info("==@Before== gwy blog logger : begin");
    }


    /**
     * 后置/最终通知：无论目标方法在执行过程中出现一场都会在它之后调用
     */
    @After("cutMethod()")
    public void after() {
        System.out.println("==@After== lingyejun blog logger : finally returning");
    }

    /**
     * 后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行
     */

    @AfterReturning("cutMethod()")
    public void afterReturning() {
        System.out.println("==@AfterReturning== lingyejun blog logger : after returning");

    }



    /**
     * 异常通知：目标方法抛出异常时执行
     */
    @AfterThrowing("cutMethod()")
    public void afterThrowing() {
        System.out.println("==@AfterThrowing== lingyejun blog logger : after throwing");

    }

    //这种是针对切面方法 @Pointcut(POINT_CUT)，这个可以针对更广泛的内容
    @Around(value ="cutMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint){
        logger.info("@Around环绕通知："+joinPoint.getSignature().toString());
        Object obj = null;
        try {
            obj = joinPoint.proceed(); //可以加参数
            logger.info(obj.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.info("@Around环绕通知执行结束");
        return obj;

    }


    /**
     * 环绕通知：灵活自由的在目标方法中切入代码
     */
    //2.7 @annotation: 带有相应标注的任意方法，比如@Transactional
    //这种是指定的类
    @Around(value = "@annotation(log)")
    public Object doAround(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        Object response = null;
        try {
            this.printRequest(joinPoint, log);
            long startTime = System.currentTimeMillis();
            response = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            this.printResponse(log, startTime, endTime, response);
        } catch (Throwable e) {
            logger.error("----->doAround is error:{}",e);
        }
        return response;
    }


    private void printRequest(ProceedingJoinPoint joinPoint, Log log) {
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        // 接收到请求，记录请求内容
        logger.info("---> request: desc:{}, url:{}, httpMethod:{}, args:{}",
                log.value(),
                request.getRequestURL().toString(),
                request.getMethod(),
                JSON.toJSON(joinPoint.getArgs()));
    }

    private void printResponse(Log log, long startTime, long endTime, Object response) {
        long time = this.printTime(startTime, endTime);
        // 处理完请求，返回内容
        logger.info("<--- response: desc:{}, time:{}ms, body:{}", log.value(), time, JSON.toJSON(response));
    }

    private long printTime(long startTime, long endTime) {
        return endTime - startTime;
    }


}