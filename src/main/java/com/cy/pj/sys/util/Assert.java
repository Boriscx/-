package com.cy.pj.sys.util;

public class Assert {

    public static void isNull(Object object, String message) {
        if (object == null) throw new RuntimeException(message);
    }

    public static void isNoNull(Object object,String message){
        if (object!=null) throw new RuntimeException(message);
    }

    public static void isEmtry(Object object, String message) {
        if (object == null || object.toString().isEmpty()) throw new RuntimeException(message);
    }

    public static void isNull(Integer pageCurrent,String message){
        if (pageCurrent == null || pageCurrent<1) throw new RuntimeException(message);
    }

}
