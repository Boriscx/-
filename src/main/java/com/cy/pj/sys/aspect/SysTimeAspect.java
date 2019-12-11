package com.cy.pj.sys.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class SysTimeAspect {

    @Pointcut("bean(sysUserServiceImpl)")
    public void doTime(){}

    @Before("doTime()")
    public void doBefore() throws Throwable{
        System.out.println("time before :");
        //Object result = joinPoint.proceed();
    }

    @After("doTime()")
    public void doAfter()throws Throwable{
        //return pointJoin.proceed();
        System.out.println("time after");
    }

    @AfterReturning("doTime()")
    public void doAfterReturning(){
        System.out.println("time afterReturning");
    }

    @AfterThrowing(value = "doTime()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e){
        System.out.println("time is a");
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        String methodName = ms.getName();
        log.error("{} exception msg is {}",methodName,e.getMessage());
    }

}
