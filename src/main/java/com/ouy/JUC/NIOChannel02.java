package com.ouy.JUC;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOChannel02 {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\OUY\\Desktop\\崂山网格化\\曙光原系统\\曙光原系统迁移分析.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
