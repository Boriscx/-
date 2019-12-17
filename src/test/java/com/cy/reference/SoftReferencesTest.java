package com.cy.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用
 * 当内存不足时执行GC
 */
public class SoftReferencesTest {

    public static void main(String[] args) {
        SoftReference<ClassA> sr = new SoftReference<>(new ClassA());

    }

}
