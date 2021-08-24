package com.xiazhenyu.transport;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.util.concurrent.ThreadFactory;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class NettyEventLoopFactory {

    public static EventLoopGroup eventLoopGroup(int threads, String theadFactoryName) {
        ThreadFactory threadFactory = new DefaultThreadFactory(theadFactoryName, true);
        return shouldEpoll() ? new EpollEventLoopGroup(threads, threadFactory) : new NioEventLoopGroup(threads, threadFactory);

    }

    private static boolean shouldEpoll() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }


}