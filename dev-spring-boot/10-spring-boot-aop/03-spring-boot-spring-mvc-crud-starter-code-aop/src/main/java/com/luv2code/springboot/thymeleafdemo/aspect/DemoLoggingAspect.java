package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(public * com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    public void forController(){

    }

    @Pointcut("execution(public * com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    public void forService(){

    }

    @Pointcut("execution(public * com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    public void forDAO(){

    }

    @Pointcut("forController() || forService() || forDAO()")
    public void combineControllerServiceDAO(){

    }



    @Before("combineControllerServiceDAO()")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();

        myLogger.info("======>>> in @Before for method: " + method);

        Object[] args = joinPoint.getArgs();

        for(Object obj : args){
            myLogger.info("======>>> argument: " + obj);
        }
    }



    @AfterReturning(pointcut = "combineControllerServiceDAO()",
                    returning = "res")
    public void afterReturning(JoinPoint joinPoint, Object res){
        String method = joinPoint.getSignature().toShortString();

        myLogger.info("======>>> in @AfterReturning for method: " + method);

        myLogger.info("====> The returning value: " + res);
    }
}
