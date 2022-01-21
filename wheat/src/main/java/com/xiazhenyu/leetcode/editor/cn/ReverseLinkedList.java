//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：[2,1]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
// 
// 
// Related Topics 递归 链表 👍 2218 👎 0

package com.xiazhenyu.leetcode.editor.cn;

import java.util.List;

public class ReverseLinkedList {

    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();
//        System.out.println(solution.reverseList(new ListNode(1, new ListNode(2, new ListNode(3, null)))));
        ListNode head = new ListNode(1);
        ListNode node_2 = new ListNode(2, null);
        head.next = node_2;
        ListNode node_3 = new ListNode(3, null);
        node_2.next = node_3;
        ListNode node_4 = new ListNode(4, null);
        node_3.next = node_4;
        ListNode node_5 = new ListNode(5, null);
        node_4.next = node_5;
        System.out.println(solution.reverseList(head));


    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode() {} ListNode(int val) {
     * this.val = val; } ListNode(int val, ListNode next) { this.val = val; this.next = next; } }
     */
    class Solution {

        public ListNode reverseList(ListNode head) {

           /* ListNode reverse = head;
            ListNode temp = head;
            while (head.next != null) {
                temp = reverse;
                reverse = head.next;
                reverse.next = temp;
                head = head.next;
            }
            return reverse;*/

            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}