package com.xiazhenyu.wheat.listNode;

/**
 * @Author xiazhenyu
 * @Description
 * @Date 11:02 下午 2021/6/1
 **/
public class RemoveNthFromEnd {


    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0);

        dummy.next = head;

        int length = 0;

        ListNode len = head;
        while (len != null) {
            length++;
            len = len.next;
        }

        length = length - n;
        ListNode target = dummy;
        while (length > 0) {
            target = target.next;
            length--;
        }
        target.next = target.next.next;
        return dummy.next;

    }


    public ListNode removeNthFromEndByFastSlowPointer(ListNode head, int n) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node1 = dummy, node2 = dummy;
        while (node1 != null) {
            node1 = node1.next;
            if (n < 1 &&node1 != null){
                node2 = node2.next;
            }
            n--;
        }

        node2.next = node2.next.next;
        return  dummy.next;
    }


}
