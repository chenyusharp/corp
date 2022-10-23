package com.xiazhenyu.cucumber.cache;

import java.io.Serializable;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MutableObj<T> implements Mutable<T>, Serializable {

    private static final long serialVersionUID = 1907779637297389456L;


    public static <T> MutableObj<T> of(T value) {
        return new MutableObj<>(value);
    }


    private T value;

    public MutableObj() {
    }

    public MutableObj(final T value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MutableObj<?> that = (MutableObj<?>) o;
        return this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value == null ? 0 : value.hashCode();
    }

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public void set(final T value) {
        this.value = value;
    }
}