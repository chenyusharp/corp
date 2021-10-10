package com.xiazhenyu.corn.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;

/**
 * Date: 2021/10/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
class RSemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        final String key="Semaphore-test";
        RedissonClient client = Redisson.create();
        RBucket<String> bucket=client.getBucket(key);
        bucket.set("100");
        RSemaphore semaphore = client.getSemaphore(key);
        semaphore.acquire();
        System.out.println("get semaphore----");

//        System.out.println("get Semaphore-test:"+bucket.get());

        semaphore.release();
        System.out.println("start release----");
        client.shutdown();
    }


}