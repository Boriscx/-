package com.cy.pj.sys.util;

public class PageUtil {
    public static void isValid(Integer pageCurrent){
        if (pageCurrent == null || pageCurrent < 1) throw new IllegalArgumentException("页码值无效");
    }
}
