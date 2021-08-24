package com.xiazhenyu.registry;

import java.util.List;
import javax.xml.ws.Service;
import org.apache.curator.x.discovery.ServiceInstance;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface Registry<T> {

    void registryService(ServiceInstance<T> serviceInstance) throws Exception;


    void unRegistryServie(ServiceInstance<T> serviceInstance) throws Exception;


    List<ServiceInstance<T>> queryForInstance(String name) throws Exception;


}
