package com.xiazhenyu.wheat.listNode;

/**
 * Date: 2025/1/24
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DoublyListNode {

    int val;
    DoublyListNode next;
    DoublyListNode prev;

    public DoublyListNode(int x) {
        val = x;
    }

    DoublyListNode createDoublyListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        DoublyListNode head = new DoublyListNode(arr[0]);
        DoublyListNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            DoublyListNode newNode = new DoublyListNode(arr[i]);
            cur.next = newNode;
            newNode.prev = cur;
            cur = cur.next;
        }
        return head;
    }


}