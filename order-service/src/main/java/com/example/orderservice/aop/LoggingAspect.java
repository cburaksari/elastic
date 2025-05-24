package com.example.orderservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.example.orderservice.service.OrderService.*(..))")
    public void orderServiceMethods() {}

    @Before("orderServiceMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("[Before] {} called with args: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "orderServiceMethods()", returning = "result")
    public void logAfterReturningMethod(JoinPoint joinPoint, Object result) {
        logger.info("[AfterReturning] {} returned: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "orderServiceMethods()", throwing = "ex")
    public void logAfterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        logger.error("[Exception] {} threw exception: {}", joinPoint.getSignature(), ex.toString());
    }
}
