package com.xiazhenyu.registry;

import static java.util.stream.Collectors.toList;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceCache;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.InstanceSerializer;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ZookeeperRegistry<T> implements Registry<T> {


    private Map<String, List<ServiceInstanceListener<T>>> listener = Maps.newConcurrentMap();

    private InstanceSerializer serializer = new JsonInstanceSerializer(ServerInfo.class);

    private ServiceDiscovery<T> serviceDiscovery;

    private ServiceCache<T> serviceCache;

    private String address = "localhost:2181";

    public void start() throws Exception {
        String root = "/demo/rpc";
        CuratorFramework client = CuratorFrameworkFactory.newClient(address, new ExponentialBackoffRetry(1000, 3));
        client.start();

        serviceDiscovery = ServiceDiscoveryBuilder.builder(ServerInfo.class)
                .client(client).basePath(root)
                .serializer(serializer)
                .build();
        serviceDiscovery.start();

        serviceCache = serviceDiscovery.serviceCacheBuilder()
                .name("/demoService")
                .build();

        client.blockUntilConnected();
        serviceDiscovery.start();
        serviceCache.start();


    }

    @Override
    public void registryService(ServiceInstance<T> serviceInstance) throws Exception {
        serviceDiscovery.registerService(serviceInstance);
    }

    @Override
    public void unRegistryServie(ServiceInstance<T> serviceInstance) throws Exception {
        serviceDiscovery.unregisterService(serviceInstance);
    }

    @Override
    public List<ServiceInstance<T>> queryForInstance(String name) throws Exception {
        return serviceCache.getInstances().stream().filter(s -> s.getName().equals(name))
                .collect(toList());
    }
}