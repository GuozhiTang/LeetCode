/*
 * @lc app=leetcode id=25 lang=java
 *
 * [25] Reverse Nodes in k-Group
 *
 * https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 *
 * algorithms
 * Hard (39.47%)
 * Likes:    1699
 * Dislikes: 334
 * Total Accepted:    234.4K
 * Total Submissions: 592.8K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 * 
 * k is a positive integer and is less than or equal to the length of the
 * linked list. If the number of nodes is not a multiple of k then left-out
 * nodes in the end should remain as it is.
 * 
 * 
 * 
 * 
 * Example:
 * 
 * Given this linked list: 1->2->3->4->5
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * For k = 3, you should return: 3->2->1->4->5
 * 
 * Note:
 * 
 * 
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be
 * changed.
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // Time: O(nk)
    // Space: O(1)
    public ListNode reverseKGroup(ListNode head, int k) {
        // 0 -> 1 -> 2 -> 3 -> 4 -> 5
        // beg                 end
        // Reverse it to:
        // 0 -> 3 -> 2 -> 1 -> 4 -> 5
        // beg                 end

        // Corner Cases
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);  // 0
        dummy.next = head; // 0 -> 1 -> 2 -> 3 -> 4 -> 5
        ListNode begin = dummy, phead = head;

        int count = 0;
        while (phead != null) {
            count++;
            phead = phead.next;
            if (count == k) { // this is the kth after begin
                begin = reverse(begin, phead);
                count = 0;
            }
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode begin, ListNode end) {
        ListNode head = begin.next;
        ListNode cur = begin.next.next;

        while (cur != end) {
            ListNode next = cur.next;
            cur.next = begin.next;
            begin.next = cur;
            cur = next;
        }
        // let head point to the rest of list
        head.next = cur;
        return head;
    }
}
// @lc code=end

