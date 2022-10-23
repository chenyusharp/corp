package com.xiazhenyu.cucumber.cache;

import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public abstract class ReentrantCache<K, V> extends AbstractCache<K, V> {


    private static final long serialVersionUID = -2367283137203413019L;

    protected final ReentrantLock lock = new ReentrantLock();


    @Override
    public void put(K key, V object, long timeout) {
        lock.lock();
        try {
            putWithOutLock(key, object, timeout);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean containsKey(K key) {
        lock.lock();
        try {
            final CacheObj<K, V> co = getWithoutLock(key);
            if (co == null) {
                return false;
            }
            if (false == co.isExpired()) {
                return true;
            }
        } finally {
            lock.unlock();
        }
        remove(key, true);
        return false;
    }

    private void remove(K key, boolean withMissCount) {
        CacheObj<K, V> co;
        lock.lock();
        try {
            co = removeWithoutLock(key, withMissCount);
        } finally {
            lock.unlock();
        }
        if (null != co) {
            onRemove(co.key, co.obj);
        }
    }

    @Override
    public V get(K key, boolean isUpdateLastAccess) {
        CacheObj<K, V> co;
        lock.lock();
        try {
            co = getWithoutLock(key);
        } finally {
            lock.unlock();
        }
        if (null == co) {
            missCount.increment();
            return null;
        } else if (false == co.isExpired()) {
            hitCount.increment();
            return co.get(isUpdateLastAccess);
        }
        remove(key, true);
        return null;
    }

    @Override
    public Iterator<CacheObj<K, V>> cacheObjIterator() {
        CopiedIter<CacheObj<K, V>> copiedIteror;
        lock.lock();
        try {
            copiedIteror = CopiedIter.copyOf(cacheObjIter());
        } finally {
            lock.unlock();
        }
        return new CacheObjIterator<>(copiedIteror);
    }


    @Override
    public int prune() {
        lock.lock();
        try {
            return pruneCache();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void remove(K key) {
        remove(key, false);
    }

    @Override
    public void clear() {
        lock.lock();
        try {
            cacheMap.clear();
        } finally {
            lock.unlock();
        }
    }


}