package com.xiazhenyu.common.core.builder;

import com.xiazhenyu.common.lang.Consumer3;
import com.xiazhenyu.common.lang.Supplier1;
import com.xiazhenyu.common.lang.Supplier2;
import com.xiazhenyu.common.lang.Supplier3;
import com.xiazhenyu.common.lang.Supplier4;
import com.xiazhenyu.common.lang.Supplier5;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Date: 2022/11/6
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class GenericBuilder<T> implements Builder<T> {

    private static final long serialVersionUID = 5548502155837938066L;


    private final Supplier<T> instant;


    private final List<Consumer<T>> modifiers = new ArrayList<>();


    public GenericBuilder(Supplier<T> instant) {
        this.instant = instant;
    }


    public static <T> GenericBuilder<T> of(Supplier<T> instant) {
        return new GenericBuilder<>(instant);
    }


    public static <T, P1> GenericBuilder<T> of(Supplier1<T, P1> instant, P1 p1) {
        return of(instant.toSupplier(p1));
    }


    public static <T, P1, P2> GenericBuilder<T> of(Supplier2<T, P1, P2> instant, P1 p1, P2 p2) {
        return of(instant.toSupplier(p1, p2));
    }

    public static <T, P1, P2, P3> GenericBuilder<T> of(Supplier3<T, P1, P2, P3> instant, P1 p1, P2 p2, P3 p3) {
        return of(instant.toSupplier(p1, p2, p3));
    }

    public static <T, P1, P2, P3, P4> GenericBuilder<T> of(Supplier4<T, P1, P2, P3, P4> instant, P1 p1, P2 p2, P3 p3, P4 p4) {
        return of(instant.toSupplier(p1, p2, p3, p4));
    }


    public static <T, P1, P2, P3, P4, P5> GenericBuilder<T> of(Supplier5<T, P1, P2, P3, P4, P5> instant, P1 p1, P2 p2, P3 p3,
            P4 p4, P5 p5) {
        return of(instant.toSupplier(p1, p2, p3, p4, p5));
    }


    public GenericBuilder<T> with(Consumer<T> consumer) {
        modifiers.add(consumer);
        return this;
    }


    public <P1> GenericBuilder<T> with(BiConsumer<T, P1> consumer, P1 p1) {
        modifiers.add(instant -> consumer.accept(instant, p1));
        return this;
    }


    public <P1, P2> GenericBuilder<T> with(Consumer3<T, P1, P2> consumer, P1 p1, P2 p2) {
        modifiers.add(instant -> consumer.accept(instant, p1, p2));
        return this;
    }


    @Override
    public T build() {
        T value = instant.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }
}