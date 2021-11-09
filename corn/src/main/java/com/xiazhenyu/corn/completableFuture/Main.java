package com.xiazhenyu.corn.completableFuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Date: 2021/10/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Main {


    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();

    static int getMoreData() {
        System.out.println("begin to start compute");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute.passed " + (System.currentTimeMillis() - t) / 1000 + " second");
        return rand.nextInt(1000);
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Main::getMoreData);

        Future<Integer> f = future.whenComplete((v, e) -> {
            System.out.println(v);
            System.out.println(e);
        });
        System.out.println(f.get());
//        System.in.read();

    }

}