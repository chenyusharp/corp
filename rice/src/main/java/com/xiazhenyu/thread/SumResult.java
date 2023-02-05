package com.xiazhenyu.thread;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BinaryOperator;
import lombok.SneakyThrows;

/**
 * Date: 2021/11/14
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SumResult<R> {

    /**
     * 任务返回值
     */
    private final R R;

    /**
     * 执行任务的方法
     */
    private final BinaryOperator<R> reduce;

    /**
     * 任务返回值类型
     */
    private final Class<?> returnType;

    public SumResult(R r, BinaryOperator<R> reduce) {
        R = r;
        this.reduce = reduce;
        this.returnType = r.getClass();
    }

    public void addAll(R r) {
        reduce.apply(R, r);
    }


    public R getR() {
        return R;
    }

    public static <V> SumResult<List<V>> build(Class<V> v) {
        CopyOnWriteArrayList<V> list = new CopyOnWriteArrayList<>();
        return new SumResult<>(list, (u, l) -> {
            u.addAll(l);
            return u;
        });
    }

    public static <R> SumResult<R> build(R o, BinaryOperator<R> reduce) {
        return new SumResult<>(o, reduce);
    }


    @SneakyThrows
    public <R> SumResult<R> create() {
        return new SumResult<>((R) returnType.newInstance(), (BinaryOperator<R>) reduce);
    }


}