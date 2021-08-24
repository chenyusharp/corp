package com.xiazhenyu;

import com.xiazhenyu.proxy.DemoRpcProxy;
import com.xiazhenyu.registry.ServerInfo;
import com.xiazhenyu.registry.ZookeeperRegistry;

/**
 * Date: 2021/8/19
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ZookeeperRegistry<ServerInfo> discovery = new ZookeeperRegistry<>();
        discovery.start();
        DemoService demoService = DemoRpcProxy.newInstance(DemoService.class, discovery);
        String result = demoService.sayHello("hello");
        System.out.println(result);

    }

}