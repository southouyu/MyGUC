package com.ouy.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    public volatile Map<String,String> map = new HashMap<>();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void setValue(String key,String value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread()+"开始写入数据key"+key+"\t value"+value);
            map.put(key,value);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread()+"\t 写入数据完成");
        }catch (InterruptedException e){
        }finally {
            lock.writeLock().unlock();
        }
    }
    public void getValue(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread()+"开始读取数据key"+key);
            String v = map.get(key);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread()+"\t 读取数据为"+v);
        }catch (InterruptedException e){
        }finally {
            lock.readLock().unlock();
        }
    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache cache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() ->{
                cache.setValue(String.valueOf(tempInt),String.valueOf(tempInt));
            },String.valueOf(i)).start();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        }catch (Exception e){

        }
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() ->{
                cache.getValue(String.valueOf(tempInt));
            },String.valueOf(i)).start();
        }
    }
}
