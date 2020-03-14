package com.ouy.JUC;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOChannel01 {

    public static void main(String[] args) throws Exception{
        String str = "hello";
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");
        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());

        byteBuffer.flip();
        channel.write(byteBuffer);

        fileOutputStream.close();

    }
}
