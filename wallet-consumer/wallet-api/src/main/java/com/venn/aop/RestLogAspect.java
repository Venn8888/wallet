package com.venn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 接口出入参切面
 * <p>
 *
 * @author : venn
 * @date : 2019/11/7
 **/
@Component
@Aspect
@Order(1)
public class RestLogAspect {

    private static Logger logger = LoggerFactory.getLogger(RestLogAspect.class);

    /**
     * 定义一个切入点
     */
    @Pointcut("execution(* com.venn.controller..*.*(..))")
    public void executePointCut() {
    }

    @Around("executePointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();

        String classAndMethodName = proceedingJoinPoint.getSignature().toShortString();
        try {
            logger.info("{} request param:{}", classAndMethodName,
                    proceedingJoinPoint.getArgs());
            Object result = proceedingJoinPoint.proceed();
            logger.info("{} usedTime:{}ms,response:{}", classAndMethodName,
                    beginTime - System.currentTimeMillis(), result);
            return result;
        } catch (Throwable e) {
            logger.info("{} usedTime:{}throw exception:", classAndMethodName,
                    beginTime - System.currentTimeMillis(), e);
            throw e;
        }
    }

}

