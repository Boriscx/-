package com.cy.sys.log;
import org.junit.jupiter.api.Test;
import java.lang.reflect.ParameterizedType;

public class BeanTest {

    @Test
    void test(){
        SuperClass<String> c = new SuperClass<String>();
        System.out.println(c.gettClass());
    }


}


class SuperClass<T>{
    Class<T> tClass;

    public SuperClass(){


        System.out.println((ParameterizedType)getClass().getGenericSuperclass());

    }

    public Class<T> gettClass() {
        return tClass;
    }
}