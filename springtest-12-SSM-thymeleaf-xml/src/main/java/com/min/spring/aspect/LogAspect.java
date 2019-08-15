package com.min.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    protected static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //定义一个切点
    @Pointcut("execution(public * com.min.spring.service..*(..))")
    public void pt1(){}

    //通知中指定上面定义的切点1
    @Before("pt1()")
    public void start(JoinPoint joinPoint) {
        logger.info("service start====" + joinPoint.getSignature().getName());
    }

    @After("pt1()")
    public void after(JoinPoint joinPoint) {
        logger.info("service after====" + joinPoint.getSignature().getName());
    }

    //直接在通知中写明起点,不需要专门定义切点
    @Before("execution(public * com.min.spring.controller..*(..))")
    public void cstart(JoinPoint joinPoint) {
        logger.info("controller start====" + joinPoint.getSignature().getName());
    }

    @After("execution(public * com.min.spring.controller..*(..))")
    public void cafter(JoinPoint joinPoint) {
        logger.info("controller after====" + joinPoint.getSignature().getName());
    }
}
