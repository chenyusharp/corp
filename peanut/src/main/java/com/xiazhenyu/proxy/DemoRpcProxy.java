package com.xiazhenyu.proxy;

import static com.xiazhenyu.Constants.DEFAULT_TIMEOUT;
import static com.xiazhenyu.Constants.MAGIC;
import static com.xiazhenyu.Constants.VERSION_1;

import com.xiazhenyu.protocol.Header;
import com.xiazhenyu.protocol.Message;
import com.xiazhenyu.protocol.Request;
import com.xiazhenyu.protocol.Response;
import com.xiazhenyu.registry.Registry;
import com.xiazhenyu.registry.ServerInfo;
import com.xiazhenyu.transport.Connection;
import com.xiazhenyu.transport.DemoRpcClient;
import com.xiazhenyu.transport.NettyResponseFuture;
import io.netty.channel.ChannelFuture;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.curator.x.discovery.ServiceInstance;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DemoRpcProxy implements InvocationHandler {


    private String serviceNmae;
    private Map<Method, Header> headerCache = new ConcurrentHashMap<>();
    private Registry<ServerInfo> registry;


    public DemoRpcProxy(String serviceNmae,
            Registry<ServerInfo> registry) {
        this.serviceNmae = serviceNmae;
        this.registry = registry;
    }

    public static <T> T newInstance(Class<T> clazz, Registry<ServerInfo> registry) throws Exception {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz},
                new DemoRpcProxy("demoService", registry));

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<ServiceInstance<ServerInfo>> serviceInstances = registry.queryForInstance(serviceNmae);
        ServiceInstance<ServerInfo> selectServiceInstance = serviceInstances
                .get(ThreadLocalRandom.current().nextInt(serviceInstances.size()));
        String methodName = method.getName();
        Header header = headerCache.computeIfAbsent(method, h -> new Header(MAGIC, VERSION_1));
        Message<Request> message = new Message<>(header, new Request(serviceNmae, methodName, args));
        return remoteCall(selectServiceInstance.getPayload(), message);
    }

    private Object remoteCall(ServerInfo payload, Message<Request> message)
            throws ExecutionException, InterruptedException, TimeoutException {
        if (payload == null) {
            throw new RuntimeException("get available server  error");
        }
        Object result;
        try {
            DemoRpcClient demoRpcClient = new DemoRpcClient(payload.getHost(), payload.getPort());
            ChannelFuture channelFuture = demoRpcClient.connect().awaitUninterruptibly();
            Connection connection = new Connection(channelFuture, true);
            NettyResponseFuture<Response> responseFuture = connection.request(message, DEFAULT_TIMEOUT);
            result = responseFuture.getPromise().get(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }


}