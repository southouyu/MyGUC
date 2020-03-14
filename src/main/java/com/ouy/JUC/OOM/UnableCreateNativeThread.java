package com.ouy.JUC.OOM;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.Executors.newFixedThreadPool;

//linux用户下面可以一个进程创建1024个线程
//
public class UnableCreateNativeThread {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2, 2, 1000,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(5));
        for (int i = 0; ; i++) {
            System.out.println("创建了"+i+"个线程");
            threadPoolExecutor.execute( new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            },String.valueOf(i)));

        }
    }
}
