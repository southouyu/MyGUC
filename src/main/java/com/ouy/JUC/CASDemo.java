package com.ouy.JUC;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        System.out.println(atomicInteger.compareAndSet(10,100) +"\t"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(10,1000)+"\t"+atomicInteger.get());
    }
}
