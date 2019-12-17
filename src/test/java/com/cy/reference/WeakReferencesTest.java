package com.cy.reference;

import java.lang.ref.WeakReference;
import java.util.Objects;

// 弱引用

/**
 * 弱引用,只要触发了GC就可能被直接回收
 */
public class WeakReferencesTest {

    public static void main(String[] args) {
        WeakReference<ClassA> classAWeakReference = new WeakReference<>(new ClassA());
        // ClassA  o2  = classAWeakReference.get();/// 将弱引用转换为抢引用
        Objects.requireNonNull(classAWeakReference.get()).doShow();
        System.gc();
    }

}
