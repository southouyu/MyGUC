package com.ouy.JUC;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceQueneDemo {
    public static void main(String[] args) {
        Object o = new Object();
        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();
        WeakReference<Object> objectWeakReference = new WeakReference<>(o, objectReferenceQueue);

        System.out.println(o);
        System.out.println(objectWeakReference.get());
        System.out.println(objectReferenceQueue.poll());

        o = null;
        System.gc();
        System.out.println(o);
        System.out.println(objectWeakReference.get());
        System.out.println(objectReferenceQueue.poll());
    }
}
