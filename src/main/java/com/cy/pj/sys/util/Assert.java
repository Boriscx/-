package com.cy.pj.sys.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

public class Assert {

    public static void isNull(Object object, String message) {
        if (object == null) throw new RuntimeException(message);
    }

    public static void isNoNull(Object object, String message) {
        if (object != null) throw new RuntimeException(message);
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
