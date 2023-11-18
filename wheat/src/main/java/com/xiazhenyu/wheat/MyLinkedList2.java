package com.xiazhenyu.wheat;

import com.sun.tools.internal.xjc.reader.xmlschema.parser.LSInputSAXWrapper;

/**
 * Date: 2023/8/19
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyLinkedList2<E> {

    private static class Node<E> {

        E val;
        Node<E> next;

        public Node(E val) {
            this.val = val;
        }
    }

    private final Node<E> head, tail;
    private int size;

    public MyLinkedList2() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        this.size = 0;
    }

    public void addFirst(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = head.next;
        head.next = x;
        x.next = temp;
        size++;
    }

    public void addLast(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp;
        if (size - 1 >= 0) {
            temp = getNode(size - 1);
        } else {
            temp = head;
        }
        x.next = tail;
        temp.next = x;
        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == size) {
            addLast(element);
            return;
        }
        Node<E> x = new Node<>(element);
        Node<E> p = getNode(index);
        Node<E> temp;
        if (index - 1 >= 0) {
            temp = getNode(index - 1);
        } else {
            temp = head;
        }

        x.next = p;
        temp.next = x;
        size++;
    }

    public E removeFirst(){

    }



    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }



    private Node<E> getNode(int index) {
        Node<E> p = head.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 检查 index 索引位置是否可以存在元素
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * 检查 index 索引位置是否可以添加元素
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }


}