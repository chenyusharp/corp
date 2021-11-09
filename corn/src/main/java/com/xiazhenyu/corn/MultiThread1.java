package com.xiazhenyu.corn;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Date: 2021/10/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MultiThread1 {

    public static void main(String[] args) {
        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(() -> {
                    sleep(2);
                    return "A";
                });

        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    sleep(3);
                    return "B";
                });

        CompletableFuture<String> f3 =
                f1.applyToEither(f2, s -> s);
        System.out.println(f3.join());
    }

    private static void sleep(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}