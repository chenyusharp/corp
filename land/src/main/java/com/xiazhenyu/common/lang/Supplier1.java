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
public interface Supplier1<T, P1> {


    T get(P1 p1);


    default Supplier<T> toSupplier(P1 p1) {
        return () -> get(p1);
    }


}
