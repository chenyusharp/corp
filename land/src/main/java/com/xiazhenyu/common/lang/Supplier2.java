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
public interface Supplier2<T, P1, P2> {

    T get(P1 p1, P2 p2);


    default Supplier<T> toSupplier(P1 p1, P2 p2) {
        return () -> get(p1, p2);
    }


}
