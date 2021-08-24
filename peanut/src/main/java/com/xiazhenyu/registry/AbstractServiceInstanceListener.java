package com.xiazhenyu.registry;

import org.apache.curator.x.discovery.ServiceInstance;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public abstract class AbstractServiceInstanceListener<T> implements ServiceInstanceListener<T> {


    public void onFresh(ServiceInstance<T> serviceInstance, ServerInfoEvent serverInfoEvent) {
        switch (serverInfoEvent) {
            case ON_REGISTRY:
                onRegistry(serviceInstance);
                break;
            case ON_REMOVE:
                onRemove(serviceInstance);
                break;
            case ON_UPDATE:
                onUpdate(serviceInstance);
                break;
        }

    }

}