package com.ouy.JUC;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerNoSafeDemo {
    public static void main(String[] args) {
        Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String,String> map1 = new ConcurrentHashMap<>();
        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,7));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void listNoSafe() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,7));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
