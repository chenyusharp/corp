package com.xiazhenyu.wheat;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Date: 2023/8/13
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyArrayList<E> implements Iterable<E> {

    private E[] data;
    private int size;
    private static final int INIT_CAP = 1;


    public MyArrayList() {
        this(INIT_CAP);
    }


    public MyArrayList(int initCapacity) {
        data = (E[]) new Object[initCapacity];
        size = 0;
    }

    public void addLst(E e) {
        int cap = data.length;
        if (size == cap) {
            resize(2 * cap);
        }
        data[size] = e;
        size++;
    }


    private void add(int index, E e) {
        checkPositionIndex(index);
        final int cap = data.length;
        if (size == cap) {
            resize(2 * cap);
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = e;
        size++;
    }


    public void addFirst(E e) {
        add(0, e);
    }


    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        final int cap = data.length;
        if (size == cap / 4) {
            resize(cap / 2);
        }
        E deletedVal = data[size - 1];
        data[size - 1] = null;
        size--;
        return deletedVal;
    }


    public E remove(int index) {
        checkPositionIndex(index);

        final int cap = data.length;
        if (size == cap / 4) {
            resize(cap / 2);
        }
        final E deleteVal = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[size - 1] = null;
        size--;
        return deleteVal;
    }

    public E remnoveFirst() {
        return remove(0);
    }

    public E get(int index) {
        checkPositionIndex(index);
        return data[index];
    }


    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ",Size:" + size);
        }
    }


    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
        }
    }


    public E set(int index, E element) {
        checkPositionIndex(index);
        E oldVal = data[index];
        data[index] = element;
        return oldVal;
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 & index < size;
    }


    private void resize(int newCap) {
        if (size > newCap) {
            return;
        }
        E[] temp = (E[]) new Object[newCap];
        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int p = 0;

            @Override
            public boolean hasNext() {
                return p != size;
            }

            @Override
            public E next() {
                return data[p++];
            }
        };
    }
}