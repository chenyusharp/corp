package com.xiazhenyu;

import com.xiazhenyu.factory.BeanManager;
import com.xiazhenyu.registry.ServerInfo;
import com.xiazhenyu.registry.ZookeeperRegistry;
import com.xiazhenyu.transport.DemoRpcServer;
import org.apache.curator.x.discovery.ServiceInstance;

/**
 * Date: 2021/8/19
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        BeanManager.registerBean("demoService", new DemoServiceImpl());
        ZookeeperRegistry<ServerInfo> discovery = new ZookeeperRegistry<>();
        discovery.start();
        ServerInfo serverInfo = new ServerInfo("127.0.0.1", 20880);
        discovery.registryService(ServiceInstance.<ServerInfo>builder().name("demoServie").payload(serverInfo).build());
        DemoRpcServer rpcServer = new DemoRpcServer(20880);
        rpcServer.start();
        Thread.sleep(100000000L);

    }

}