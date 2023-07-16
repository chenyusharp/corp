package com.xiazhenyu.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2021/8/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@SuppressWarnings("NullableProblems")
@Slf4j
public class Cache {

    private static LoadingCache<String, String> cacheMap;


    public static void main(String[] args) {
        cacheMap = CacheBuilder.newBuilder()
                .maximumSize(100)
                .refreshAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return writeCache(key);
                    }
                });

        System.out.println(cacheMap.getUnchecked("cache"));
        System.out.println(cacheMap.getUnchecked("cache"));
    }


    public static String writeCache(String key) {
        log.info("触发缓存刷新操作");
        return String.valueOf(System.identityHashCode(key));
    }


}