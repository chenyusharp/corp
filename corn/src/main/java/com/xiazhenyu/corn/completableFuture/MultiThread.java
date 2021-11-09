package com.xiazhenyu.corn.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Date: 2021/10/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MultiThread {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return 100;
        });


//        future.join();
        future.get();
    }

}