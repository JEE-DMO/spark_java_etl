package com.example.reportgenerator.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.reportgenerator..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info(methodName + " - Starting");
        try {
            Object result = joinPoint.proceed();
            logger.info(methodName + " - Finished");
            return result;
        } catch (Throwable throwable) {
            logger.error(methodName + " - Exception", throwable);
            throw throwable;
        }
    }
}
