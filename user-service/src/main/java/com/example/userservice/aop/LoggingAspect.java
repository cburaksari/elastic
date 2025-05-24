package com.example.userservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.example.userservice.service.UserService.*(..))")
    public void userServiceMethods() {}

    @Before("userServiceMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("[Before] {} called with args: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "userServiceMethods()", returning = "result")
    public void logAfterReturningMethod(JoinPoint joinPoint, Object result) {
        logger.info("[AfterReturning] {} returned: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "userServiceMethods()", throwing = "ex")
    public void logAfterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        logger.error("[Exception] {} threw exception: {}", joinPoint.getSignature(), ex.toString());
    }
}