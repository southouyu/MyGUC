package com.ouy.JUC;

import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) {
        WeakHashMap<Integer,String> weakHashMap = new WeakHashMap();
        Integer key = new Integer("1");
        String str = "Map";
        weakHashMap.put(key,str);
        System.out.println(weakHashMap);
        key = null;
        System.out.println(weakHashMap);
        System.gc();
        System.out.println(weakHashMap+"\t"+weakHashMap.size());

    }
}
