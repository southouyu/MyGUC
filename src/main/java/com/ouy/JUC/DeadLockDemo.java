package com.ouy.JUC;

import java.util.concurrent.TimeUnit;

class LockDemo implements Runnable{
    private String lockA;
    private String lockB;

    public LockDemo(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t占用"+lockA+"\t尝试获取"+lockB);
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){};
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t占用"+lockB+"\t尝试获取"+lockA);
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "locka";
        String lockB = "lockb";

        new Thread(()->new LockDemo(lockA,lockB),"a").start();
        new Thread(()->new LockDemo(lockB,lockA),"b").start();
    }
}
