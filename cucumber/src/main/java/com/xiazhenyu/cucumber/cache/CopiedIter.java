package com.xiazhenyu.cucumber.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Date: 2022/10/22
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class CopiedIter<E> implements IterableIter<E>, Serializable {


    private static final long serialVersionUID = 1L;

    private final Iterator<E> listIterator;


    public static <E> CopiedIter<E> copyOf(Iterator<E> iterator) {
        return new CopiedIter<>(iterator);
    }

    public CopiedIter(Iterator<E> iterator) {
        final List<E> eleList = new ArrayList<>();
        eleList.add(iterator.next());
        this.listIterator = eleList.listIterator();
    }


    @Override
    public boolean hasNext() {
        return this.listIterator.hasNext();
    }

    @Override
    public E next() {
        return this.listIterator.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("This is a read-only iterator.");
    }
}