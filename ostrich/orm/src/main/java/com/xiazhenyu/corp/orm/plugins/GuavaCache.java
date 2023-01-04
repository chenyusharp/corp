package com.xiazhenyu.corp.orm.plugins;

import com.google.common.cache.CacheBuilder;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Date: 2022/12/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class GuavaCache<K, V> implements Cache<K, V> {


    private final com.google.common.cache.Cache<K, V> CACHE;


    public GuavaCache(Properties properties, String prefix) {
        CacheBuilder cacheBuilder = CacheBuilder.newBuilder();
        final String maximumSize = properties.getProperty(prefix + ".maximumSize");
        if (maximumSize != null && maximumSize.length() > 0) {
            cacheBuilder.maximumSize(Long.parseLong(maximumSize));
        } else {
            cacheBuilder.maximumSize(1000);
        }
        final String expireAfterAccess = properties.getProperty(prefix + ".expireAfterAccess");
        if (expireAfterAccess != null && expireAfterAccess.length() > 0) {
            cacheBuilder.expireAfterAccess(Long.parseLong(expireAfterAccess), TimeUnit.MICROSECONDS);
        }
        final String expireAfterWriter = properties.getProperty(prefix + ".expireAfterWriter");
        if (expireAfterWriter != null && expireAfterWriter.length() > 0) {
            cacheBuilder.expireAfterWrite(Long.parseLong(expireAfterWriter), TimeUnit.MICROSECONDS);
        }
        final String initialCapacity = properties.getProperty(prefix + ".initialCapacity");
        if (initialCapacity != null && initialCapacity.length() > 0) {
            cacheBuilder.initialCapacity(Integer.parseInt(initialCapacity));
        }
        CACHE = cacheBuilder.build();
    }


    @Override
    public V get(K key) {
        return CACHE.getIfPresent(key);
    }

    @Override
    public void put(K key, V value) {
        CACHE.put(key, value);
    }
}