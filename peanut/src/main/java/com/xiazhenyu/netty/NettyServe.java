package com.xiazhenyu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Date: 2021/10/16
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class NettyServe {


    public static void main(String[] args) throws InterruptedException {
        //创建bossGroup线程组：处理网络事件--连接事件 线程数默认为2倍于处理器线程数
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //创建workerGroup线程组：处理网络事件--读写事件 2*线程器线程数
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //创建服务端启动助手
        ServerBootstrap bootstrap = new ServerBootstrap();
        //设置线程组
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)//设置服务端通道实现；
                .option(ChannelOption.SO_BACKLOG, 128)//参数设置-设置线程队列中等待连接个数
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)//参数设置-设置活跃状态，child是设置workergroup
                .childHandler(new ChannelInitializer<SocketChannel>() { //创建第一个初始化对象
                    //向pipleline中添加自定义的业务处理handler
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyServerHandler());
                    }
                });
        //启动服务端并绑定端口，同时将异步改同步；
        ChannelFuture future = bootstrap.bind(777).sync();

        System.out.println("服务器启动成功。。。");
        //关闭通道（并不是真正意义上的关闭，而是监听通道关闭状态）和关闭连接
        future.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();


    }

}