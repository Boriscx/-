package com.cy.pj.sys.aspect;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class SysCacheAspect {

    @Around("@annotation(com.cy.pj.sys.aspect.annotation.RequiredCache)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        return null;
    }

}
