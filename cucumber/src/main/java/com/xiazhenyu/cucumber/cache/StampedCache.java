package com.xiazhenyu.cucumber.cache;

import java.util.Iterator;
import java.util.concurrent.locks.StampedLock;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public abstract class StampedCache<K, V> extends AbstractCache<K, V> {


    protected final StampedLock lock = new StampedLock();


    @Override
    public void put(K key, V object, long timeout) {
        final long stamp = lock.writeLock();
        try {
            putWithOutLock(key, object, timeout);
        } finally {
            lock.unlockWrite(stamp);
        }

    }

    @Override
    public V get(K key, boolean isUpdateLastAccess) {
        long stamp = lock.tryOptimisticRead();
        CacheObj<K, V> co = getWithoutLock(key);
        if (false == lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                co = getWithoutLock(key);
            } finally {
                lock.unlockRead(stamp);
            }
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
        CopiedIter<CacheObj<K, V>> copiedIterator;
        final long stamp = lock.readLock();
        try {
            copiedIterator = CopiedIter.copyOf(cacheObjIter());
        } finally {
            lock.unlockRead(stamp);
        }
        return new CacheObjIterator<>(copiedIterator);
    }

    @Override
    public int prune() {
        final long stamp = lock.writeLock();
        try {
            return pruneCache();
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    @Override
    public void remove(K key) {
        remove(key, false);
    }

    @Override
    public void clear() {
        final long stamp = lock.writeLock();
        try {
            cacheMap.clear();
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    @Override
    public boolean containsKey(K key) {
        final long stamp = lock.readLock();
        try {
            final CacheObj<K, V> co = getWithoutLock(key);
            if (co == null) {
                return false;
            }
            if (false == co.isExpired()) {
                return true;
            }
        } finally {
            lock.unlockRead(stamp);
        }
        remove(key, true);
        return false;
    }


    private void remove(K key, boolean withMissCount) {
        final long stamp = lock.writeLock();
        CacheObj<K, V> co;
        try {
            co = removeWithoutLock(key, withMissCount);
        } finally {
            lock.unlockWrite(stamp);
        }
        if (null != co) {
            onRemove(co.key, co.obj);
        }

    }
}