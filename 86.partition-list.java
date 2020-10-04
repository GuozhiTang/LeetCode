/*
 * @lc app=leetcode id=86 lang=java
 *
 * [86] Partition List
 *
 * https://leetcode.com/problems/partition-list/description/
 *
 * algorithms
 * Medium (39.66%)
 * Likes:    989
 * Dislikes: 257
 * Total Accepted:    195.1K
 * Total Submissions: 490.9K
 * Testcase Example:  '[1,4,3,2,5,2]\n3'
 *
 * Given a linked list and a value x, partition it such that all nodes less
 * than x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * 
 * Example:
 * 
 * 
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
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
    public ListNode partition(ListNode head, int x) {
        // Corner Cases
        if (head == null || head.next == null) return head;

        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);
        ListNode phead1 = dummy1, phead2 = dummy2;

        while (head != null) {
            if (head.val < x) phead1 = phead1.next = head;
            else phead2 = phead2.next = head;
            head = head.next;
        }

        phead1.next = dummy2.next;
        // point to null
        phead2.next = null;
        return dummy1.next;
    }
}
// @lc code=end

