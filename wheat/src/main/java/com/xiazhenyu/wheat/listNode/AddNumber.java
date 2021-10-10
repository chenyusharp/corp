package com.xiazhenyu.wheat.listNode;

import java.util.List;

/**
 * Date: 2021/10/6
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class AddNumber {


    public class Solution {


        int carry = 0;

        ListNode dummy = new ListNode();

        public ListNode addTwoNumbers(ListNode first, ListNode second) {
            //链表的遍历
            //链表的遍历
            ListNode result = dummy;
            while (first != null || second != null) {
                int value = 0;
                value = value + first.val;
                value = value + second.val;
                value = value + carry;
                carry = value / 10;
                result.next = new ListNode(value % 10);
                first = first.next;
                second = second.next;
                result = result.next;
            }
            if (carry > 0) {
                result.next = new ListNode(carry);
            }
            return dummy.next;
        }
    }
}