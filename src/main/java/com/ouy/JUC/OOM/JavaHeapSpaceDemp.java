package com.ouy.JUC.OOM;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class JavaHeapSpaceDemp {
    public static void main(String[] args) {
        String string = "sugon";
        while (true){
            string +=string+new Random().nextInt(1111111111)+new Random().nextInt(22222222);
            string.intern();
        }
    }
}
