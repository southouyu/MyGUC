package com.ouy.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Data {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increate() {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            condition.signalAll();
            num++;
            System.out.println(Thread.currentThread().getName() + "\t increate value is" + num);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    public void decreate() {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            condition.signalAll();
            num--;
            System.out.println(Thread.currentThread().getName() + "\t decreatee value is" + num);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }
}

public class Producter_demo {

    public static void main(String[] args) {
        Data data = new Data();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                data.increate();
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                data.decreate();
            },String.valueOf(i)).start();
        }
    }

}
