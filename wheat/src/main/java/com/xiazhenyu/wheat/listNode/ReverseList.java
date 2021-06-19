package com.xiazhenyu.wheat.listNode;

import java.lang.invoke.CallSite;

/**
 * @Author xiazhenyu
 * @Description
 * @Date 5:11 下午 2021/5/5
 **/
public class ReverseList {


    public ListNode reverseList(ListNode head) {
        ListNode next = null;
        ListNode pre = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre=head;
            head = next;
        }
        return pre;

    }




    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        new ReverseList().reverseList(a);
        while (e != null) {
            System.out.println(e.val);
            e = e.next;
        }


    }

}
