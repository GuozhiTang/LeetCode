/*
 * @lc app=leetcode id=203 lang=java
 *
 * [203] Remove Linked List Elements
 *
 * https://leetcode.com/problems/remove-linked-list-elements/description/
 *
 * algorithms
 * Easy (37.06%)
 * Likes:    1199
 * Dislikes: 69
 * Total Accepted:    284.1K
 * Total Submissions: 765.7K
 * Testcase Example:  '[1,2,6,3,4,5,6]\n6'
 *
 * Remove all elements from a linked list of integers that have value val.
 * 
 * Example:
 * 
 * 
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
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
    // Time: O(n)
    // Space: O(1)
    public ListNode removeElements(ListNode head, int val) {
        // Corner Cases
        if (head == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head;

        while (cur != null) {
            if (cur.val == val) {
                ListNode next = cur.next;
                pre.next = next;
                cur = next;
                continue;
            }
            cur = cur.next;
            pre = pre.next;
        }
        return dummy.next;
    }
}
// @lc code=end

