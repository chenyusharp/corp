package com.xiazhenyu.corn.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * Date: 2021/10/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class LockExamples {


    public static void main(String[] args) throws InterruptedException {
        RedissonClient client = Redisson.create();
        RLock lock = client.getLock("lock");

        lock.lock();

        System.out.println("lock acquired");

        Thread t = new Thread(() -> {
            RLock lock1 = client.getLock("lock");
            lock1.lock();
            System.out.println("lock acquired by thread");
            lock1.unlock();
            System.out.println("lock released by thread");
        });
        t.start();
        t.join(1000);

        lock.unlock();
        System.out.println("lock released");

        t.join();
        client.shutdown();






    }


}