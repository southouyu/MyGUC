package com.ouy.JUC;

import java.lang.ref.SoftReference;

public class SoftRefrenceDemo {

    public static void enoughSpace(){
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o);
        System.out.println(o);
        System.out.println(softReference.get());

        o = null;
        System.gc();
        System.out.println(o);
        System.out.println(softReference.get());
    }

    public static void notEnough(){
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o);
        System.out.println(o);
        System.out.println(softReference.get());

        try {
            o = null;
            byte[] bytes = new byte[30 * 1024 * 1024];
        }finally {
            System.out.println(o);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args) {
//        enoughSpace();
        notEnough();
    }
}
