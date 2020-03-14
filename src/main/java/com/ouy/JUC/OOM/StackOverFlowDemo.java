package com.ouy.JUC.OOM;

public class StackOverFlowDemo {

    public static void main(String[] args) {
        stackOverFlow();
    }

    private static void stackOverFlow(){
        stackOverFlow();
    }
}
