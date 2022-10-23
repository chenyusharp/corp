package com.xiazhenyu.cucumber.cache;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class CacheObjIterator<K, V> implements Iterator<CacheObj<K, V>>, Serializable {


    private static final long serialVersionUID = -8486852266413684598L;


    private final Iterator<CacheObj<K, V>> iterator;

    private CacheObj<K, V> nextValue;


    public CacheObjIterator(Iterator<CacheObj<K, V>> iterator) {
        this.iterator = iterator;
        nextValue();
    }


    @Override
    public boolean hasNext() {
        return nextValue != null;
    }

    @Override
    public CacheObj<K, V> next() {
        if (false == hasNext()) {
            throw new NoSuchElementException();
        }
        final CacheObj<K, V> cacheObj = nextValue;
        nextValue();
        return cacheObj;
    }

    public void remove() {
        throw new UnsupportedOperationException("Cache values Iterator is not support to modify.");
    }


    private void nextValue() {
        while (iterator.hasNext()) {
            nextValue = iterator.next();
            if (nextValue.isExpired() == false) {
                return;
            }
        }
        nextValue = null;
    }


}