package com.ouy.JUC;

import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScatteringAndGatheringDemo {
    public static void main(String[] args) throws  Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        serverSocketChannel.socket().bind(inetSocketAddress);


        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        SocketChannel accept = serverSocketChannel.accept();
        int messageLenght = 8;
        while (true){
            int byteRead = 0;
            while (byteRead < messageLenght){
               Long l = accept.read(byteBuffers);
               byteRead += l;
                System.out.println("byteRead="+byteRead);
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "positioin"+
                        byteBuffer.position()+", limit="+byteBuffer.limit()).forEach(System.out::println);
            }
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());

            long byteWrite = 0;

            while (byteWrite < messageLenght){
               Long l = accept.write(byteBuffers);
               byteWrite += l;
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead:="+byteRead+" byteWrite:="+byteWrite);
        }
    }
}
