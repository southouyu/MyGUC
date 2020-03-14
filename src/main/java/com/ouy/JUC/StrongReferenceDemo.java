package com.ouy.JUC;

import java.util.Objects;

public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o = new Object();
        Object objects = o;
        o = null;
        System.gc();
        System.out.println(objects);
    }
}
