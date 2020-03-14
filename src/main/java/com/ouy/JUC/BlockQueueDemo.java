package com.ouy.JUC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueDemo {
    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t add 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t add 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t add 3");
                blockingQueue.put("3");
            }catch (InterruptedException e){
            }
        },"aaa").start();

        new Thread(()-> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "\t take 1");
                blockingQueue.take();
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "\t take 2");

                blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + "\t add 3");
                blockingQueue.take();
            } catch (InterruptedException e) {
            }
        },"bbb").start();
    }

    private static void BlockQueue() throws InterruptedException {
        BlockingQueue<String> blockingDeque = new ArrayBlockingQueue<>(3);
//        System.out.println(blockingDeque.add("a"));
//        System.out.println(blockingDeque.add("b"));
//        System.out.println(blockingDeque.add("c"));
//
//        System.out.println(blockingDeque.element());
//        System.out.println(blockingDeque.element());
//
//        System.out.println(blockingDeque.remove());
//        System.out.println(blockingDeque.element());

        blockingDeque.put("a");
        blockingDeque.put("b");
        blockingDeque.put("c");
        blockingDeque.put("e");

        blockingDeque.take();
    }
}
