/*
 * @lc app=leetcode id=206 lang=java
 *
 * [206] Reverse Linked List
 *
 * https://leetcode.com/problems/reverse-linked-list/description/
 *
 * algorithms
 * Easy (59.46%)
 * Likes:    3571
 * Dislikes: 82
 * Total Accepted:    832.6K
 * Total Submissions: 1.4M
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * Reverse a singly linked list.
 * 
 * Example:
 * 
 * 
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * 
 * 
 * Follow up:
 * 
 * A linked list can be reversed either iteratively or recursively. Could you
 * implement both?
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
    // Time: O(n)
    // Space: O(1)
    // Iteratively
    // public ListNode reverseList(ListNode head) {
    //     // Corner Cases
    //     if (head == null || head.next == null) return head;

    //     ListNode dummy = new ListNode(0);
    //     dummy.next = head;
    //     ListNode pre = dummy, cur = head.next;

    //     while (cur != null) {
    //         ListNode next = cur.next;
    //         cur.next = pre.next;
    //         pre.next = cur;
    //         cur = next;
    //     }

    //     // Operate the head
    //     head.next = null;

    //     return dummy.next;
    // }

    // Time: O(n)
    // Space: O(n)
    // Recursively
    public ListNode reverseList(ListNode head) {
        // Corner Cases
        if (head == null || head.next == null) return head;

        ListNode next = head.next;
        ListNode newHead = reverseList(next);
        next.next = head;
        head.next = null;

        return newHead;
    }
}
// @lc code=end

