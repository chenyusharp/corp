package com.xiazhenyu.cucumber.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class FiFOCache<K, V> extends StampedCache<K, V> {


    private static final long serialVersionUID = -4056271480316721455L;


    public FiFOCache(int capacity) {
        this(capacity, 0);
    }

    public FiFOCache(int capacity, long timeout) {
        this.capacity = capacity;
        this.timeout = timeout;
        cacheMap = new LinkedHashMap<>(capacity + 1, 1.0f, false);
    }

    @Override
    protected int pruneCache() {
        int count = 0;
        CacheObj<K, V> first = null;
        final Iterator<CacheObj<K, V>> values = cacheObjIter();
        if (isPruneExpiredActive()) {
            while (values.hasNext()) {
                CacheObj<K, V> co = values.next();
                if (co.isExpired()) {
                    values.remove();
                    onRemove(co.key, co.obj);
                    count++;
                    continue;
                }
                if (first == null) {
                    first = co;
                }
            }
        } else {
            first = values.hasNext() ? values.next() : null;
        }
        if (isFull() && null != first) {
            removeWithoutLock(first.key, false);
            onRemove(first.key, first.obj);
            count++;
        }
        return count;
    }
}