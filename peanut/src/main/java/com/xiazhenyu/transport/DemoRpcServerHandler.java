package com.xiazhenyu.transport;

import com.xiazhenyu.Constants;
import com.xiazhenyu.protocol.Message;
import com.xiazhenyu.protocol.Request;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DemoRpcServerHandler extends SimpleChannelInboundHandler<Message<Request>> {


    private static Executor executor= Executors.newCachedThreadPool();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message<Request> msg) throws Exception {

        byte extraInfo=msg.getHeader().getExtraInfo();
        if (Constants.isHeartBeat(extraInfo)){
            ctx.writeAndFlush(msg);
            return;
        }
        executor.execute(new InvokerRunnable(msg,ctx));


    }
}