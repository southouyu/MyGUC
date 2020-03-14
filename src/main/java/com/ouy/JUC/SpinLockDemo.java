package com.ouy.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class SpinLock{
    AtomicReference atomicReference = new AtomicReference();
    public void getLock(){
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null,thread)){
        }
        System.out.println(Thread.currentThread().getName()+"\t get Lock");
    }
    public void unLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t release Lock");
    }
}

public class SpinLockDemo {
    public static void main(String[] args)  {
        SpinLock spinLock = new SpinLock();
        new Thread(()->{
            spinLock.getLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e){
            }
            spinLock.unLock();
        },"AAA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
        }
        new Thread(()->{
            spinLock.getLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
            }
            spinLock.unLock();
        },"BBB").start();

    }



}
