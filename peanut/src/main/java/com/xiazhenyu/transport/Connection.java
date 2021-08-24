package com.xiazhenyu.transport;

import com.xiazhenyu.Constants;
import com.xiazhenyu.protocol.Header;
import com.xiazhenyu.protocol.Message;
import com.xiazhenyu.protocol.Request;
import com.xiazhenyu.protocol.Response;
import io.netty.channel.ChannelFuture;
import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;
import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Connection implements Closeable {


    private static final AtomicLong ID_GENERTATOR = new AtomicLong(0);
    public static final Map<Long, NettyResponseFuture<Response>> IN_FLIGHT_REQUEST_MAP = new ConcurrentHashMap<>();

    private ChannelFuture future;

    private AtomicBoolean isConnection = new AtomicBoolean();


    public Connection() {
        this.isConnection.set(false);
        this.future = null;
    }


    public Connection(ChannelFuture future, boolean isConnected) {
        this.future = future;
        this.isConnection.set(isConnected);
    }


    public ChannelFuture getFuture() {
        return future;
    }

    public void setFuture(ChannelFuture future) {
        this.future = future;
    }

    public AtomicBoolean getIsConnection() {
        return isConnection;
    }

    public void setIsConnection(AtomicBoolean isConnection) {
        this.isConnection = isConnection;
    }


    public NettyResponseFuture<Response> request(Message<Request> message, long timeOut) {
        long messageId = ID_GENERTATOR.incrementAndGet();
        message.getHeader().setMessageId(messageId);
        NettyResponseFuture responseFuture = new NettyResponseFuture(System.currentTimeMillis(), timeOut, message,
                future.channel(), new DefaultPromise(new DefaultEventLoop()));
        IN_FLIGHT_REQUEST_MAP.put(messageId, responseFuture);
        try {
            future.channel().writeAndFlush(message);
        } catch (Exception e) {
            IN_FLIGHT_REQUEST_MAP.remove(messageId);
            throw e;
        }
        return responseFuture;
    }

    public boolean ping() {
        Header heartBeatHeader = new Header(Constants.MAGIC, Constants.VERSION_1);
        heartBeatHeader.setExtraInfo(Constants.HEAT_EXTRA_INFO);
        Message message = new Message(heartBeatHeader, null);
        NettyResponseFuture<Response> request = request(message, Constants.DEFAULT_TIMEOUT);
        try {
            Promise<Response> await = request.getPromise().await();
            return await.get().getCode() == Constants.HEARTBEAT_CODE;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public void close() throws IOException {
        future.channel().close();
    }


}