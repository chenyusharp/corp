package com.xiazhenyu.cucumber.cache;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface CacheListener<K, V> {

    void onRemove(K key, V cachedObject);

}
