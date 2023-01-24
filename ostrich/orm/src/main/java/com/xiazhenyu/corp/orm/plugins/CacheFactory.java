package com.xiazhenyu.corp.orm.plugins;

import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Date: 2022/12/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public abstract class CacheFactory {


    public static <K, V> Cache<K, V> createCache(String sqlCacheClass, String prefix, Properties properties) {
        if (sqlCacheClass != null) {
            try {
                Class.forName("com.google.common.cache.Cache");
                return new GuavaCache<>(properties, prefix);
            } catch (ClassNotFoundException e) {
                // return simpleCache
                return new SimpleCache<>(properties, prefix);
            }
        } else {
            try {
                Class<? extends Cache> clazz = (Class<? extends Cache>) Class.forName(sqlCacheClass);

                Constructor<? extends Cache> constructor = clazz.getConstructor(Properties.class, String.class);
                return constructor.newInstance(properties, prefix);

            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }


}