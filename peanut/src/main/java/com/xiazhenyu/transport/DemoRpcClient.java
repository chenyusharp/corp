package com.xiazhenyu.transport;

import com.xiazhenyu.Constants;
import com.xiazhenyu.codec.DemoRpcDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import io.netty.channel.socket.SocketChannel;
import java.io.Closeable;
import java.io.IOException;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DemoRpcClient implements Closeable {

    protected Bootstrap clientBootstrap;
    protected EventLoopGroup group;
    private String host;
    private int port;

    public DemoRpcClient(String host, int port) {
        this.host = host;
        this.port = port;
        clientBootstrap = new Bootstrap();
        group = NettyEventLoopFactory.eventLoopGroup(Constants.DEFAULT_IO_THREADS, "NettyClientWorker");
        clientBootstrap.group(group)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .channel(NioSctpChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("demo-rpc-encoder", new DemoRpcDecoder());
                        ch.pipeline().addLast("demo-rpc-decoder", new DemoRpcDecoder());
                        ch.pipeline().addLast("client-handler", new DemoRpcClientHandler());
                    }
                });
    }

    public ChannelFuture connect() {
        ChannelFuture connect = clientBootstrap.connect(host, port);
        connect.awaitUninterruptibly();
        return connect;
    }


    @Override
    public void close() throws IOException {
        group.shutdownGracefully();
    }
}