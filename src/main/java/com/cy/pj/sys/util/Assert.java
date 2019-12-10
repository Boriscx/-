package com.cy.pj.sys.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

public class Assert {

    /**
     * 验证有效,当有效性成立时返回异常信息,否则无
     *
     * @param flag    有效性
     * @param message 异常信息
     */
    public static void isValid(boolean flag, String message) {
        if (flag) throw new RuntimeException(message);
    }

    /**
     * 断言为空
     * <p>object==null,输出异常信息</p>
     *
     * @param object  对象
     * @param message 异常信息
     */
    public static void isNull(Object object, String message) {
        if (object == null) throw new RuntimeException(message);
    }

    /**
     * 断言对象不为空
     * 当对象不为空时输出异常信息
     *
     * @param object  对象
     * @param message 异常信息
     */
    public static void isNoNull(Object object, String message) {
        if (object != null) throw new RuntimeException(message);
    }

    public static void isEmpty(String str,String message){
        if (str == null || "".equals(str.trim()) || str.isEmpty() ) throw new RuntimeException(message);
    }

    public static void isEmpty(Object object, String message) {
        isNull(object, message);
        if (object instanceof String && "".equals(((String) object).trim())) {
            throw new RuntimeException(message);
        } else if (object instanceof Collection && ((Collection) object).isEmpty()) {
            throw new RuntimeException(message);
        }
    }

    public static void isNoNull(Integer o, String message) {
        if (o != null && o > 0) throw new RuntimeException(message);

    }

    public static void isNull(Integer pageCurrent, String message) {
        if (pageCurrent == null || pageCurrent < 1) throw new RuntimeException(message);
    }

}
