package com.xiazhenyu.common.lang;

import java.util.function.Supplier;

/**
 * Date: 2022/11/6
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@FunctionalInterface
public interface Supplier5<T, P1, P2, P3, P4, P5> {


    T get(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);


    default Supplier<T> toSupplier(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
        return () -> get(p1, p2, p3, p4, p5);
    }

}