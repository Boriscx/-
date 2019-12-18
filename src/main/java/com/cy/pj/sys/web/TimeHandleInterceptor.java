package com.cy.pj.sys.web;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;


public class TimeHandleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=============TimeHandleInterceptor==========");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 9);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        long startTime = c.getTimeInMillis();
        c.set(Calendar.HOUR_OF_DAY, 21);
        long endTime = c.getTimeInMillis();
        long nowTime = System.currentTimeMillis();
        if (nowTime < startTime || nowTime > endTime) {
            throw new RuntimeException("请在9~18点之间访问");
        }
        return true;
    }

}
