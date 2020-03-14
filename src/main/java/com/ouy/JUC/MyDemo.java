package com.ouy.JUC;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDemo {


    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        List emptyList = Collections.EMPTY_LIST;
        Map<String, String> map1 = Collections.synchronizedMap(map);
        System.out.println(3|9);
    }
}
