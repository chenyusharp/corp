package com.xiazhenyu.corn.completableFuture;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * Date: 2021/10/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MultiThread {


    protected static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5,
            10,
            0,
            TimeUnit.MICROSECONDS,
            new ArrayBlockingQueue<>(500),
            new CallerRunsPolicy()
    );


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return 100;
        });

//        future.join();
        future.get();*/

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        List<CompletableFuture<Integer>> featureList = list.stream()
                .map(shippingRuleDO ->
                        CompletableFuture.supplyAsync(() -> {
                            int count = new Random().nextInt(10);
                            System.out.println(count);
                            return count;
                        }, executor)
                                .exceptionally(e -> {
//                                    log.warn("shippingRuleMapper#updateByPrimaryKeySelective error", e);
                                    return 0;
                                }))
                .collect(toList());
        int count = featureList.stream().mapToInt(CompletableFuture::join).sum();
        System.out.println("====" + count);
    }

}