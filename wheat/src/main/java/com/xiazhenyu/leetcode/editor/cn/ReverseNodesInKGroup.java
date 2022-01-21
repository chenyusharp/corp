//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 进阶： 
//
// 
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？ 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
// 
//
// 示例 4： 
//
// 
//输入：head = [1], k = 1
//输出：[1]
// 
//
// 
// 
//
// 提示： 
//
// 
// 列表中节点的数量在范围 sz 内 
// 1 <= sz <= 5000 
// 0 <= Node.val <= 1000 
// 1 <= k <= sz 
// 
// Related Topics 递归 链表 👍 1443 👎 0

package com.xiazhenyu.leetcode.editor.cn;

public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        Solution solution = new ReverseNodesInKGroup().new Solution();

        /*ListNode head = new ListNode(1);
        ListNode node_2 = new ListNode(2, null);
        head.next = node_2;
        ListNode node_3 = new ListNode(3, null);
        node_2.next = node_3;
        ListNode node_4 = new ListNode(4, null);
        node_3.next = node_4;
        ListNode node_5 = new ListNode(5, null);
        node_4.next = node_5;
        ListNode result = solution.reversezKGroup(head, 2);
        System.out.println(result);*/
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode() {} ListNode(int val) {
     * this.val = val; } ListNode(int val, ListNode next) { this.val = val; this.next = next; } }
     */
    class Solution {

        public ListNode reverseKGroup(ListNode head, int k) {

            if (head == null || head.next == null) {
                return head;
            }
            ListNode tail = head;
            for (int i = 0; i < k; i++) {
                if (tail == null) {
                    return head;
                }
                tail = tail.next;
            }

            ListNode newHead = reverse(head, tail);
            head.next = reverseKGroup(tail, k);
            return newHead;

        }


        private ListNode reverse(ListNode head, ListNode tail) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != tail) {
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