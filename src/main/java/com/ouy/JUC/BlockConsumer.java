package com.ouy.JUC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class CacheData{
    private volatile Boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String>  blockingQueue = null;

    public CacheData(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(BlockingQueue.class.getName());
    }

    public void producter() throws Exception{
        String data = null;
        Boolean reFlag ;
        while (flag){
            data = atomicInteger.incrementAndGet()+"";
            reFlag = blockingQueue.offer(data,2,TimeUnit.SECONDS);
            if(reFlag){
                System.out.println(Thread.currentThread().getName()+"\t 生产数据"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 生产数据"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("生产停止、取消生产");
    }

    public void consumter() throws Exception{
        String data = null;
        while (flag){
            data = blockingQueue.poll(2,TimeUnit.SECONDS);
            if(null == data || data.equalsIgnoreCase("")){
                flag = false;
                System.out.println("超过时长没有收到信息、已停止消费");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费数据为"+data);
        }
    }
    public void stop(){
        this.flag = false;
    }
}

public class BlockConsumer {
    public static void main(String[] args) throws Exception{
        CacheData cacheData = new CacheData(new ArrayBlockingQueue(3));
        new Thread(()->{
            try {
                cacheData.producter();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"producter").start();

        new Thread(()->{
            try {
                cacheData.consumter();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"consumer").start();

        TimeUnit.SECONDS.sleep(10);
        cacheData.stop();
    }
}

