package com.xiazhenyu.cucumber.cache;

import java.util.Iterator;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class LRUCache<K, V> extends ReentrantCache<K, V> {

    private static final long serialVersionUID = 8914283680593041543L;


    public LRUCache(int capacity) {
        this(capacity, 0);
    }

    public LRUCache(int capacity, long timeout) {
        if (Integer.MAX_VALUE == capacity) {
            capacity -= 1;
        }
        this.capacity = capacity;
        this.timeout = timeout;
        cacheMap = new FixedLinkedHashMap<>(capacity);
    }


    @Override
    protected int pruneCache() {
        if (isPruneExpiredActive() == false) {
            return 0;
        }
        int count = 0;
        Iterator<CacheObj<K, V>> values = cacheObjIter();
        CacheObj<K, V> co;
        while (values.hasNext()) {
            co = values.next();
            if (co.isExpired()) {
                values.remove();
                onRemove(co.key, co.obj);
                count++;
            }
        }
        return count;
    }
}