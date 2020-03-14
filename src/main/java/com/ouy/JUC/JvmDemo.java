package com.ouy.JUC;

import java.util.concurrent.TimeUnit;

public class JvmDemo {
    public static void main(String[] args) throws Exception{
        Runtime.getRuntime().totalMemory();
        Runtime.getRuntime().maxMemory();
    }
}
