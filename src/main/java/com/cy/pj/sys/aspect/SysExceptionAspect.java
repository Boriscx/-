package com.cy.pj.sys.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Slf4j
@Aspect
@Component
public class SysExceptionAspect {

//    @AfterThrowing(value = "bean(*ServiceImpl)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        String methodName = ms.getName();
        log.error("{} exception msg is {}", methodName, e.getMessage());
    }

}
