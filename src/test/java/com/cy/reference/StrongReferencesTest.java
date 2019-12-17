package com.cy.reference;

public class StrongReferencesTest {
    public static void main(String[] args) {
        ClassA o1 = new ClassA();// o1为classA对象实例的强引用

        o1 = null;// 此时ClassA的实例不可达

        System.gc();// 手动启动GC(告诉JVM有时间过来回收一些内存)


    }
}
