package com.xiazhenyu.transport;

import com.xiazhenyu.Constants;
import com.xiazhenyu.protocol.Message;
import com.xiazhenyu.protocol.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DemoRpcClientHandler extends SimpleChannelInboundHandler<Message<Response>> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message<Response> msg) throws Exception {
        NettyResponseFuture responseFuture = Connection.IN_FLIGHT_REQUEST_MAP.remove(msg.getHeader().getMessageId());

        Response response = msg.getContent();
        if (response == null && Constants.isHeartBeat(msg.getHeader().getExtraInfo())) {
            response = new Response();
            response.setCode(Constants.HEARTBEAT_CODE);
        }
        responseFuture.getPromise().setSuccess(response.getResult());

    }
}