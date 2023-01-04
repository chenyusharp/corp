package com.xiazhenyu.corp.orm.plugins;

/**
 * Date: 2022/12/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

}
