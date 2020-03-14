package com.ouy.JUC;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NIOChannel04 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("");
        FileOutputStream fileOutputStream = new FileOutputStream("");

        FileChannel resouce = fileInputStream.getChannel();
        FileChannel destinct = fileOutputStream.getChannel();
        destinct.transferFrom(resouce,0,resouce.size());

        resouce.close();
        destinct.close();
        fileInputStream.close();
        fileOutputStream.close();

    }
}
