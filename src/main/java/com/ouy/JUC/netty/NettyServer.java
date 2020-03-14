package com.ouy.JUC.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws Exception{
       EventLoopGroup bossGroup = new NioEventLoopGroup();
       EventLoopGroup workGroup = new NioEventLoopGroup();

       try{
           ServerBootstrap bootstrap = new ServerBootstrap();
           bootstrap.group(bossGroup,workGroup)
                   .channel(NioServerSocketChannel.class)
                   .option(ChannelOption.SO_BACKLOG,128)
                   .childOption(ChannelOption.SO_KEEPALIVE,true)
                   .childHandler(new ChannelInitializer<SocketChannel>() {
                       @Override
                       protected void initChannel(SocketChannel socketChannel) throws Exception {
                           socketChannel.pipeline().addLast(new NettyServerHandler());
                       }
                   });
           ChannelFuture cf = bootstrap.bind(6668).sync();
           cf.channel().closeFuture().sync();
       }finally {
           bossGroup.shutdownGracefully();
           workGroup.shutdownGracefully();
       }

    }
}
