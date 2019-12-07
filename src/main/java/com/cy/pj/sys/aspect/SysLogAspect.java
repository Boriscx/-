package com.cy.pj.sys.aspect;


import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.cy.pj.sys.util.IPUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SysLogAspect {
    private Logger log = Logger.getLogger(SysLogAspect.class);

    private final SysLogService sysLogService;


    public SysLogAspect(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    /**
     * 这里我们使用注解的形式
     * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     *
     * 切点表达式:   execution(...)
     */
    @Pointcut("@annotation(com.cy.pj.sys.aspect.annotation.RequestLog)")
    public void logPointCut() {
    }

    /**
     * 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
     *
     * @param jointPoint
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint //连接点
                                 jointPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        //执行目标方法(result为目标方法的执行结果)
        Object result = jointPoint.proceed();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        log.info("方法执行的总时长为:" + totalTime);
        saveSysLog(jointPoint, totalTime);
        return result;
    }

    /**
     * 保存日志
     *
     * @param point
     * @param totalTime
     */
    private void saveSysLog(ProceedingJoinPoint point, long totalTime)
            throws NoSuchMethodException, SecurityException, JsonProcessingException {
        //1.获取日志信息
        MethodSignature ms = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        String className = targetClass.getName();
        //获取接口声明的方法
        String methodName = ms.getMethod().getName();
        Class<?>[] parameterTypes = ms.getMethod().getParameterTypes();
        //获取目标对象方法(AOP版本不同,可能获取方法对象方式也不同)
        Method targetMethod = targetClass.getDeclaredMethod(
                methodName, parameterTypes);
        //获取用户名,学完shiro再进行自定义实现,没有就先给固定值
//        String username = ShiroUtils.getPrincipal().getUsername();
        String username = "bcx";
        //获取方法参数
        Object[] paramsObj = point.getArgs();
        System.out.println("paramsObj=" + paramsObj);
        //将参数转换为字符串
        String params = new ObjectMapper()
                .writeValueAsString(paramsObj);
        //2.封装日志信息
        SysLog log = new SysLog();
        log.setUsername(username);//登陆的用户
        //假如目标方法对象上有注解,我们获取注解定义的操作值
        RequestLog requestLog =
                targetMethod.getDeclaredAnnotation(RequestLog.class);
        if (requestLog != null) {
            System.out.println(getClass().getName()+".requestLog:"+requestLog.value());
            log.setOperation(requestLog.value());
        }
        log.setMethod(className + "." + methodName);//className.methodName()
        log.setParams(params);//method params
        log.setIp(IPUtils.getIpAddr());//ip 地址
        log.setTime(totalTime);//
        log.setCreatedTime(new Date());
        //3.保存日志信息
        sysLogService.saveObject(log);
    }

}

