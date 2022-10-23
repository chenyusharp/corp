package com.xiazhenyu.cucumber.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SafeConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

    private static final long serialVersionUID = 5279523878387823809L;


    public SafeConcurrentHashMap() {
        super();
    }


    public SafeConcurrentHashMap(int initialCapacity) {
        super(initialCapacity);
    }


    public SafeConcurrentHashMap(Map<? extends K, ? extends V> m) {
        super(m);
    }


    public SafeConcurrentHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }


    public SafeConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
        super(initialCapacity, loadFactor, concurrencyLevel);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return MapUtil.computeIfAbsent(this, key, mappingFunction);
    }
}