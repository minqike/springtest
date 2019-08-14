package com.min.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    protected static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(public * com.min.spring.service..*(..))")
    public void start(JoinPoint joinPoint) {
        logger.info("LogAspect service start====" + joinPoint.getSignature().getName());
    }

    @After("execution(public * com.min.spring.service..*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("LogAspect service after====" + joinPoint.getSignature().getName());
    }

    @Before("execution(public * com.min.spring.controller..*(..))")
    public void cstart(JoinPoint joinPoint) {
        logger.info("LogAspect controller start====" + joinPoint.getSignature().getName());
    }

    @After("execution(public * com.min.spring.controller..*(..))")
    public void cafter(JoinPoint joinPoint) {
        logger.info("LogAspect  controller after====" + joinPoint.getSignature().getName());
    }
}
