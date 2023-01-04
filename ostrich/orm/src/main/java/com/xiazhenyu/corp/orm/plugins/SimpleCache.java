package com.xiazhenyu.corp.orm.plugins;

import java.util.Properties;
import org.apache.ibatis.cache.decorators.FifoCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.mapping.CacheBuilder;

/**
 * Date: 2022/12/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SimpleCache<K, V> implements Cache<K, V> {


    private final org.apache.ibatis.cache.Cache CACHE;

    public SimpleCache(Properties properties, String prefix) {
        CacheBuilder cacheBuilder = new CacheBuilder("SQL_CACHE");
        final String typeClass = properties.getProperty(prefix + ".typeClass");
        if (typeClass != null && typeClass.length() > 0) {
            try {
                cacheBuilder.implementation((Class<? extends org.apache.ibatis.cache.Cache>) Class.forName(typeClass));
            } catch (ClassNotFoundException e) {
                cacheBuilder.implementation(PerpetualCache.class);
            }
        } else {
            cacheBuilder.implementation(PerpetualCache.class);
        }
        final String evictionClass = properties.getProperty(prefix + ".evictionClass");
        if (evictionClass != null && evictionClass.length() > 0) {
            try {
                cacheBuilder.addDecorator((Class<? extends org.apache.ibatis.cache.Cache>) Class.forName(evictionClass));
            } catch (ClassNotFoundException e) {
                cacheBuilder.addDecorator(FifoCache.class);
            }
        } else {
            cacheBuilder.addDecorator(FifoCache.class);
        }
        final String flushInterval = properties.getProperty(prefix + ".flushInterval");
        if (flushInterval != null && flushInterval.length() > 0) {
            cacheBuilder.clearInterval(Long.parseLong(flushInterval));
        }
        final String size = properties.getProperty(prefix + ".size");
        if (size != null && size.length() > 0) {
            cacheBuilder.size(Integer.parseInt(size));
        }
        cacheBuilder.properties(properties);
        CACHE = cacheBuilder.build();

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {

    }
}