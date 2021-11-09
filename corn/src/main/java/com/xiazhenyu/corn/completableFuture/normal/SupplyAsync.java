package com.xiazhenyu.corn.completableFuture.normal;

import static java.util.stream.Collectors.toList;

import com.google.common.collect.Lists;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPoolManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2021/11/10
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Slf4j
public class SupplyAsync {


    public static void main(String[] args) {
        List<VO> dtoList = new ArrayList();//rpc接口调用的参数集合
        List<VO> result = null;//对应的调用的rpc方法的返回结果
        Executor executor = new ForkJoinPool(20);//执行的线程池
        List<CompletableFuture<List<VO>>> featureList = Lists.partition(dtoList, 20).stream()
                .map(subQueryCondition ->
                        CompletableFuture.supplyAsync(() ->
                                Optional.ofNullable(result).orElse(Lists.newLinkedList()), executor)
                                .exceptionally(e -> {
                                    log.warn("getStockMap error", e);
                                    return Lists.newArrayList();
                                })).collect(toList());
        //并行获取
        List<VO> inventoryItemVOList = featureList.stream().flatMap(feature -> feature.join().stream())
                .collect(toList());
    }


    /**
     * 对应的并发查询的对象
     */
    public static class VO {

    }

}