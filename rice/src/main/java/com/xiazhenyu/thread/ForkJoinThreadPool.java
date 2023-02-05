package com.xiazhenyu.thread;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * Date: 2021/11/10
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ForkJoinThreadPool {

    protected final static ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 2);




    public static <T,R> List<R> getResultListWithoutException(){
         return  null;
    }

}