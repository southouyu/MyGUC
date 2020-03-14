package com.ouy.JUC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
    public synchronized void sendMS(){
        System.out.println(Thread.currentThread().getName()+"\t send MS");
        sendEmail();
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t send email");
    }

    @Override
    public void run() {
        get();
    }
    private void get(){
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t send MS");
            set();
        }catch (Exception e){
          e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    private void set(){
        Lock lock  = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t send email");
        }catch (Exception e){

        }finally {
           lock.unlock();
        }
    }
}

public class ReentLockDemo {
    public static void main(String[] args) {
        Phone p = new Phone();
        new Thread(()->{
            p.sendMS();
        },"t1").start();

        new Thread(() ->{
            p.sendMS();
        },"t2").start();

        Thread t2 = new Thread(p,"t3");
        Thread t1 = new Thread(p,"t4");
        t1.start();
        t2.start();
    }


}
