package com.xiazhenyu.transport;

import com.xiazhenyu.factory.BeanManager;
import com.xiazhenyu.protocol.Header;
import com.xiazhenyu.protocol.Message;
import com.xiazhenyu.protocol.Request;
import com.xiazhenyu.protocol.Response;
import io.netty.channel.ChannelHandlerContext;
import java.lang.reflect.Method;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class InvokerRunnable implements Runnable {


    private ChannelHandlerContext context;
    private Message<Request> message;

    public InvokerRunnable(Message<Request> msg, ChannelHandlerContext ctx) {
        this.message = msg;
        this.context = ctx;
    }

    @Override
    public void run() {
        Response response = new Response();
        Object result = null;
        try {
            Request request = message.getContent();
            String serviceName = request.getServiceName();
            Object bean = BeanManager.getBean(serviceName);
            Method method = bean.getClass().getMethod(request.getMethodName(), request.getArgTypes());
            result = method.invoke(bean, request.getArgs());

        } catch (Exception exception) {

        } finally {

        }
        Header header = message.getHeader();
        header.setExtraInfo((byte) 1);
        response.setResult(result);
        context.writeAndFlush(new Message(header, response));
    }
}