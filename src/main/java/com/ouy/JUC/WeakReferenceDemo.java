package com.ouy.JUC;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o = new Object();
        WeakReference<Object> objectWeakReference = new WeakReference<>(o);
        System.out.println(o);
        System.out.println(objectWeakReference.get());

        o = null;
        System.gc();
        System.out.println(o);
        System.out.println(objectWeakReference.get());
    }
}
