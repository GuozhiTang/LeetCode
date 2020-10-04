/*
 * @lc app=leetcode id=21 lang=java
 *
 * [21] Merge Two Sorted Lists
 *
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 *
 * algorithms
 * Easy (51.19%)
 * Likes:    3358
 * Dislikes: 494
 * Total Accepted:    846.7K
 * Total Submissions: 1.7M
 * Testcase Example:  '[1,2,4]\n[1,3,4]'
 *
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * 
 * Example:
 * 
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
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
    // Time: O(n + m)
    // Space: O(1)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Corner Cases
        if (l1 == null || l2 == null) return (l1 == null) ? l2 : l1;

        ListNode dummy = new ListNode(0);
        ListNode phead = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                phead.next = l1;
                l1 = l1.next;
                phead = phead.next;
            } else {
                phead.next = l2;
                l2 = l2.next;
                phead = phead.next;
            }
        }

        if (l1 == null) phead.next = l2;
        if (l2 == null) phead.next = l1;

        return dummy.next;
    }
}
// @lc code=end

