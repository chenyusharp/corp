package com.xiazhenyu.cucumber.cache;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface Mutable<T> {


    T get();


    void set(T value);


}
