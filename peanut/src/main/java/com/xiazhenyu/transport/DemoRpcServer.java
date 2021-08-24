package com.xiazhenyu.transport;

import com.xiazhenyu.codec.DemoRpcDecoder;
import com.xiazhenyu.codec.DemoRpcEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DemoRpcServer {

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBootstrap;
    private Channel channel;
    private int port;


    public DemoRpcServer(int port) {
        this.port = port;
        bossGroup = NettyEventLoopFactory.eventLoopGroup(1, "NettyServerBoos");
        workerGroup = NettyEventLoopFactory
                .eventLoopGroup(Math.min(Runtime.getRuntime().availableProcessors() + 1, 32), "NettyServerWorker");
        serverBootstrap = new ServerBootstrap().group(bossGroup, workerGroup)
                .channel(NioSctpServerChannel.class)
                .option(ChannelOption.SO_REUSEADDR, Boolean.TRUE)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .handler(new LoggingHandler(LogLevel.INFO)).childHandler(
                        new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast("demo-rpc-decoder", new DemoRpcDecoder());
                                ch.pipeline().addLast("demo-rpc-encoder", new DemoRpcEncoder());
                                ch.pipeline().addLast("server-handler", new DemoRpcServerHandler());
                            }
                        }
                );
    }

    public ChannelFuture start() {
        ChannelFuture channelFuture = serverBootstrap.bind(port);
        channel = channelFuture.channel();
        channel.closeFuture();
        return channelFuture;
    }









}