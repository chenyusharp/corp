package com.xiazhenyu.cucumber.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {

    private static final long serialVersionUID = 8554913225551720034L;

    protected Map<Mutable<K>, CacheObj<K, V>> cacheMap;


    protected final SafeConcurrentHashMap<K, Lock> keyLockMap = new SafeConcurrentHashMap<>();


    protected int capacity;

    protected long timeout;

    protected boolean existCustomTimeout;

    protected LongAdder hitCount = new LongAdder();

    protected LongAdder missCount = new LongAdder();

    protected CacheListener<K, V> listener;

    @Override
    public void put(K key, V object) {
        put(key, object, timeout);
    }


    protected void putWithOutLock(K key, V object, long timeout) {
        CacheObj<K, V> co = new CacheObj<>(key, object, timeout);
        if (timeout != 0) {
            existCustomTimeout = true;
        }
        if (isFull()) {

        }
        cacheMap.put(MutableObj.of(key), co);
    }

    public long getHitCount() {
        return hitCount.sum();
    }


    public long getMissCount() {
        return missCount.sum();
    }


    protected CacheObj<K, V> getWithoutLock(K key) {
        return this.cacheMap.get(MutableObj.of(key));
    }


    public V get(K key, boolean isUpdateLastAccess, Func0<V> supplier) {
        V v = get(key, isUpdateLastAccess);
        if (null == v && null != supplier) {
            final Lock keyLock = keyLockMap.computeIfAbsent(key, k -> new ReentrantLock());
            keyLock.lock();
            try {

                final CacheObj<K, V> co = getWithoutLock(key);
                if (null == co || co.isExpired()) {
                    try {
                        v = supplier.call();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    put(key, v, this.timeout);
                } else {
                    v = co.get(isUpdateLastAccess);
                }
            } finally {
                keyLock.unlock();
                keyLockMap.remove(key);
            }
        }
        return v;
    }


    @Override
    public Iterator<V> iterator() {
        CacheObjIterator<K, V> cacheObjIterator = (CacheObjIterator<K, V>) this.cacheObjIterator();
        return new CacheValuesIterator<>(cacheObjIterator);
    }


    protected abstract int pruneCache();


    public int capacity() {
        return capacity;
    }


    @Override
    public long timeout() {
        return timeout;
    }


    protected boolean isPruneExpiredActive() {
        return (timeout != 0) || existCustomTimeout;
    }

    @Override
    public boolean isFull() {
        return (capacity > 0) && (cacheMap.size() >= capacity);
    }


    @Override
    public int size() {
        return cacheMap.size();
    }

    @Override
    public boolean isEmpty() {
        return cacheMap.isEmpty();
    }


    @Override
    public String toString() {
        return cacheMap.toString();
    }

    @Override
    public Cache<K, V> setListener(CacheListener<K, V> listener) {
        this.listener = listener;
        return this;
    }


    public Set<K> keySet() {
        return this.cacheMap.keySet().stream().map(Mutable::get).collect(Collectors.toSet());
    }


    public void onRemove(K key, V cachedObject) {
        final CacheListener<K, V> listener = this.listener;
        if (null != listener) {
            listener.onRemove(key, cachedObject);
        }
    }

    protected CacheObj<K, V> removeWithoutLock(K key, boolean withMissCount) {
        final CacheObj<K, V> co = cacheMap.remove(MutableObj.of(key));
        if (withMissCount) {
            this.missCount.increment();
        }
        return co;
    }


    protected Iterator<CacheObj<K, V>> cacheObjIter() {
        return this.cacheMap.values().iterator();
    }


}