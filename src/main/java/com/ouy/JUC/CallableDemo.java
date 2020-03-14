package com.ouy.JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThead implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 100;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask(new MyThead());
        FutureTask<String> stringFutrure = new FutureTask<String>(()-> {
            return "hello world";
        });



        new Thread(futureTask,"aaa").start();

        new Thread(stringFutrure,"bbb").start();
        String resultString = stringFutrure.get();
        int result = futureTask.get();



        System.out.println(Thread.currentThread().getName()+"\t"+result);
        System.out.println(Thread.currentThread().getName()+"\t"+resultString);

    }
}
