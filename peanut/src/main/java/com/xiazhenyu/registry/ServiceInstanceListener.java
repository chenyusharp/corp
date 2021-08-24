package com.xiazhenyu.registry;

import javax.xml.ws.Service;
import org.apache.curator.x.discovery.ServiceInstance;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface ServiceInstanceListener<T> {

    void onRegistry(ServiceInstance<T> serviceInstance);


    void onRemove(ServiceInstance<T> serviceInstance);


    void onUpdate(ServiceInstance<T> serviceInstance);


    void onFresh(ServiceInstance<T> serviceInstance, ServerInfoEvent event);


    enum ServerInfoEvent {
        ON_REGISTRY,
        ON_UPDATE,
        ON_REMOVE
    }
}
