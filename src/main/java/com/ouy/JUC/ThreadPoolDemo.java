package com.ouy.JUC;

import com.sun.jmx.snmp.tasks.ThreadService;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService service  = Executors.newFixedThreadPool(5);
//        apiDemo();

        ExecutorService service = new ThreadPoolExecutor(3,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 7; i++) {
            service.execute(()->{
                try {

                    System.out.println(Thread.currentThread().getName());

                }catch (Exception e){

                }

            });
        }
        service.shutdown();

    }

    private static void apiDemo() {
        ExecutorService service  = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
           service.execute(()->{
            try {

                    System.out.println(Thread.currentThread().getName());

            }catch (Exception e){

            }

        });
        }
        service.shutdown();
    }
}
