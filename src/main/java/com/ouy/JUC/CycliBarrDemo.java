package com.ouy.JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CycliBarrDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()-> System.out.println("结束"));
        for (int i = 0; i < 7; i++) {
            final int temp = i;
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+"\t is "+temp);
                try {
                    cyclicBarrier.await();
                }catch (BrokenBarrierException | InterruptedException exception){
                }
            },String.valueOf(temp)).start();
        }
    }
}
