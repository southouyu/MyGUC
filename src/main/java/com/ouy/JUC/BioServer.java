package com.ouy.JUC;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务端启动");
        while (true){
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(()->{
                handler(socket);
            });
        }

    }


    public  static void handler(Socket socket){
        byte[] bytes = new byte[1024];
        int read = 0;
        try (InputStream inputStream = socket.getInputStream()){
            while (true){
                read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        }catch (Exception e){
        }
        finally {
            System.out.println("关闭和client的连接");
            try{
                socket.close();
            }catch (IOException e){

            };
        }
    }



}
