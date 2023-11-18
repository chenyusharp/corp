package com.xiazhenyu.wheat;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;

/**
 * Date: 2023/8/19
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyLinkedList<E> implements Iterable<E> {

    final private Node<E> head;
    final private Node<E> tail;
    private int size;

    public MyLinkedList(Node<E> head, Node<E> tail) {
        this.head = head;
        this.tail = tail;
    }


    private static class Node<E> {

        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }


    public void addLast(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = tail.prev;
        temp.next = x;
        x.prev = temp;

        x.next = tail;
        tail.prev = x;
        size++;
    }

    public void addFirst(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = head.next;
        x.prev = head;
        head.next = x;

        x.next = temp;
        temp.prev = x;
        size++;
    }


    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == size) {
            addLast(element);
            return;
        }
        Node<E> p = getNode(index);
        Node<E> temp = p.prev;
        Node<E> x = new Node<>(element);

        temp.next = x;
        x.prev = temp;

        p.prev = x;
        x.next = p;
        size++;
    }


    public E removeFirst() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        Node<E> x = head.next;
        Node<E> temp = x.next;

        head.next = temp;
        temp.prev = head;

        x.next = null;
        x.prev = null;
        size--;
        return x.val;
    }

    public E removeLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        Node<E> x = tail.prev;
        Node<E> temp = x.prev;

        tail.prev = temp;
        temp.next = tail;

        x.next = null;
        x.prev = null;
        size--;
        return x.val;
    }

    public E remove(int index) {
        checkElementIndex(index);
        Node<E> x = getNode(index);
        Node<E> prev = x.prev;
        Node<E> next = x.next;

        prev.next = next;
        next.prev = prev;

        x.next = null;
        x.prev = null;
        size--;
        return x.val;
    }


    public E get(int index) {
        checkElementIndex(index);
        final Node<E> node = getNode(index);
        return node.val;
    }


    public E getLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        return tail.prev.val;
    }


    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> p = head.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    public E set(int index, E val) {
        checkElementIndex(index);
        final Node<E> node = getNode(index);
        final E oldVal = node.val;
        node.val = val;
        return oldVal;
    }


    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }


    public void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                final E val = p.val;
                p = p.next;
                return val;
            }
        };
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
        return index >= 0 && index < size;
    }


}