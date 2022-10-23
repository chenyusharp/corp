package com.xiazhenyu.cucumber.cache;

import java.util.Iterator;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface IterableIter<T> extends Iterable<T>, Iterator<T> {

    default Iterator<T> iterator() {
        return this;
    }

}
