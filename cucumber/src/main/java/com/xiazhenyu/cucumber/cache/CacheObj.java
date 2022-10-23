package com.xiazhenyu.cucumber.cache;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class CacheObj<K, V> implements Serializable {


    private static final long serialVersionUID = -5768328461533802833L;


    protected final K key;
    protected final V obj;


    protected volatile long lastAccess;

    protected AtomicLong accessCount = new AtomicLong();


    protected final long ttl;

    protected CacheObj(K key, V obj, long ttl) {
        this.key = key;
        this.obj = obj;
        this.ttl = ttl;
        this.lastAccess = System.currentTimeMillis();
    }


    public K getKey() {
        return this.key;
    }


    public V getValue() {
        return this.obj;
    }


    public long getTtl() {
        return ttl;
    }


    public Date getExpiredTime() {
        if (ttl > 0) {
            return new Date();
        }
        return null;
    }

    public long getLastAccess() {
        return lastAccess;
    }

    protected boolean isExpired() {
        if (this.ttl > 0) {
            return (System.currentTimeMillis() - this.lastAccess) > this.ttl;
        }
        return false;
    }


    protected V get(boolean isUpdateLastAccess) {
        if (isUpdateLastAccess) {
            lastAccess = System.currentTimeMillis();
        }
        accessCount.getAndIncrement();
        return this.obj;
    }

}