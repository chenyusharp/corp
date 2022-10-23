package com.xiazhenyu.cucumber.cache;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface Cache<K, V> extends Iterable<V>, Serializable {


    int capacity();


    long timeout();


    void put(K key, V object);


    void put(K key, V object, long timeout);


    default V get(K key) {
        return get(key, true);
    }

    default V get(K key, Func0<V> supplier) {
        return get(key, true, supplier);

    }


    V get(K key, boolean isUpdateLastAccess, Func0<V> supplier);


    V get(K key, boolean isUpdateLastAccess);


    Iterator<CacheObj<K, V>> cacheObjIterator();


    int prune();

    boolean isFull();


    void remove(K key);

    void clear();

    int size();


    boolean isEmpty();


    boolean containsKey(K key);


    default Cache<K, V> setListener(CacheListener<K, V> listener) {
        return this;
    }

}
