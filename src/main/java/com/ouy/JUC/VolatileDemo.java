package com.ouy.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{
   volatile int number = 0;

   AtomicInteger atomicInteger = new AtomicInteger();

   public void atomicAdd(){
       atomicInteger.getAndIncrement();
   }
   public void add(){
       this.number = 60;
   }
   public void addPlus(){
       number++;
   }
}

public class VolatileDemo {
    public static void main(String[] args) {

    }

    private static void volatileAto() {
        MyData myData = new MyData();
        for(int i = 0;i<20;i++){
            new Thread(()->{
                for(int j = 0; j<= 1000 ; j++){
                    myData.addPlus();
                    myData.atomicAdd();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
//            Thread.currentThread().getThreadGroup().list();
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t "+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t "+myData.atomicInteger);
    }

    private static void volatileMethod() {
        MyData data = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t  coming in");
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.add();
            System.out.println(Thread.currentThread().getName()+"\t dataValue is "+data.number);
        },"myThread").start();

        while (data.number==10){

        }
        System.out.println("MainThread is close!");
    }
}
