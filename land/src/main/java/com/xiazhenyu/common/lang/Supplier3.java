package com.xiazhenyu.common.lang;

import java.util.function.Supplier;

/**
 * Date: 2022/11/6
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface Supplier3<T, P1, P2, P3> {


    T get(P1 p1, P2 p2, P3 p3);


    default Supplier<T> toSupplier(P1 p1, P2 p2, P3 p3) {
        return () -> get(p1, p2, p3);
    }


}
