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
public class HongYa {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return  "HongyaDong";
                });

        System.out.println(future.join());
    }

}