package com.ouy.JUC;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GroupChatClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6666;
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;


    public GroupChatClient() throws IOException {
        selector = Selector.open();
        socketChannel = socketChannel.open(new InetSocketAddress(HOST,PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        userName = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(userName+"\t准备好");
    }

    private void sendInfo(String info) throws IOException{
        info = userName+"\t"+info;
        socketChannel.write(ByteBuffer.wrap(info.getBytes()));
    }

    private void readInfo() throws IOException{
        int count = selector.select();
        if(count >0){
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                if(selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array()));
                }
            }
            keyIterator.remove();
        }
    }

    public static void main(String[] args) throws  Exception{
        GroupChatClient chatClient = new GroupChatClient();
        new Thread(()->{
            try {
                chatClient.readInfo();
                TimeUnit.SECONDS.sleep(3);
            }catch (IOException | InterruptedException exception){
                exception.printStackTrace();
            }

        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            chatClient.sendInfo(scanner.next());
        }
    }


}
