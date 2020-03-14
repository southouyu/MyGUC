package com.ouy.JUC;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MapBufferDemo {
    public static void main(String[] args) throws Exception{
        RandomAccessFile tw = new RandomAccessFile("1.txt", "rw");
        FileChannel channel = tw.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        map.put(0,(byte) 'H');
        map.put(3,(byte) '9');
        channel.close();
        tw.close();
    }
}
