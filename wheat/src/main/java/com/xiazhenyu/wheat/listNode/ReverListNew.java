package com.xiazhenyu.wheat.listNode;

import java.util.List;
import java.util.Stack;
import javax.xml.soap.Node;

/**
 * Date: 2021/10/6
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ReverListNew {


    public class Solution {

        ListNode dummy = new ListNode(0);
        Stack<Integer> stack = new Stack<>();
        public ListNode reverseList(ListNode list) {
            if (list == null) {
                return null;
            }
            while (list != null) {
                stack.push(list.val);
                list = list.next;
            }
            ListNode popListNode = dummy;
            while (!stack.isEmpty()) {
                popListNode.next = new ListNode(stack.pop());
                popListNode = popListNode.next;
            }
            return dummy.next;
        }

    }

}