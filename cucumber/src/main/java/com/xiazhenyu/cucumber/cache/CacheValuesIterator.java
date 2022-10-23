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
public class CacheValuesIterator<V> implements Iterator<V>, Serializable {

    private static final long serialVersionUID = 7490987375996009440L;


    private final CacheObjIterator<?, V> cacheObjIter;


    public CacheValuesIterator(CacheObjIterator<?, V> cacheObjIter) {
        this.cacheObjIter = cacheObjIter;
    }

    @Override
    public boolean hasNext() {
        return this.cacheObjIter.hasNext();
    }


    @Override
    public V next() {
        return cacheObjIter.next().getValue();
    }


    @Override
    public void remove() {
        cacheObjIter.remove();
    }
}