package com.ouy.JUC;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 6666;

    public GroupChatServer() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void lisener(){
        try{
          while (true){
              int count = selector.select(2000);
              if(count > 0){
                  Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                  while (keyIterator.hasNext()){
                      SelectionKey key = keyIterator.next();
                      //如果是可接受的
                      if(key.isAcceptable()){
                          SocketChannel socketChannel = serverSocketChannel.accept();
                          socketChannel.configureBlocking(false);
                          socketChannel.register(selector,SelectionKey.OP_READ);
                          System.out.println(socketChannel.getRemoteAddress()+"\t上线");
                      }
                      if(key.isReadable()){
                          readData(key);
                      }

                      keyIterator.remove();
                  }
              }
          }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    private void readData(SelectionKey key){
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if(count > 0){
                String msg = new String(buffer.array());
                System.out.println("读取到数据为\t"+msg);
                //向其他客户端转发消息
                sendMsgToOther(msg,channel);
            }

        }catch (Exception e){
            try {
                System.out.println(channel.getRemoteAddress()+"\t已经离线");
                key.cancel();
                channel.close();
            }catch (IOException e1){
                e1.printStackTrace();
            }


        }
    }

    private void sendMsgToOther(String msg,SocketChannel self) throws IOException{
        for (SelectionKey key : selector.selectedKeys()) {
            SocketChannel channel = (SocketChannel) key.channel();
            if(channel != self){
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                channel.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.lisener();
    }
}
