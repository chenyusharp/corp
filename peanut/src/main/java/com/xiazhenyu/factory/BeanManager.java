package com.xiazhenyu.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class BeanManager {

    public static Map<String, Object> services = new ConcurrentHashMap<>();


    public static void registerBean(String serviceName, Object bean) {
        services.put(serviceName, bean);
    }


    public static Object getBean(String serviceName) {
        return services.get(serviceName);
    }


}