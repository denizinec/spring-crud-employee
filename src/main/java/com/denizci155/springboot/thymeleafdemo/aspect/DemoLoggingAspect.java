package com.denizci155.springboot.thymeleafdemo.aspect;

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

    // set up logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // set up pointcut declarations
    @Pointcut("execution(* com.denizci155.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPkg(){}

    @Pointcut("execution(* com.denizci155.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePkg(){}

    @Pointcut("execution(* com.denizci155.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPkg(){}

    @Pointcut("forControllerPkg() || forServicePkg() || forDaoPkg()")
    private void forCombineThem(){

    }

    @Before("forCombineThem()")
    public void before(JoinPoint joinPoint){

        // display method call
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("=====> in @Before: calling method: " + method);

        // display arguments to the method

        // get arguments

        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args){
            myLogger.info("=====> argument: " + tempArg);
        }
    }

    @AfterReturning(pointcut = "forCombineThem()",
    returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){

        String method = joinPoint.getSignature().toShortString();
        myLogger.info("=====> in @AfterReturning: from method: " + method);

        myLogger.info("=====> result: " + result);

    }


}
