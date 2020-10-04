/*
 * @lc app=leetcode id=19 lang=java
 *
 * [19] Remove Nth Node From End of List
 *
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 *
 * algorithms
 * Medium (34.79%)
 * Likes:    2629
 * Dislikes: 191
 * Total Accepted:    529.6K
 * Total Submissions: 1.5M
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * Given a linked list, remove the n-th node from the end of list and return
 * its head.
 * 
 * Example:
 * 
 * 
 * Given linked list: 1->2->3->4->5, and n = 2.
 * 
 * After removing the second node from the end, the linked list becomes
 * 1->2->3->5.
 * 
 * 
 * Note:
 * 
 * Given n will always be valid.
 * 
 * Follow up:
 * 
 * Could you do this in one pass?
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Corner Cases
        if (head == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode target = findNthFromEnd(n + 1, head);

        if (target != null) {
            ListNode next = target.next.next;
            target.next = next;
            return dummy.next;
        }

        // The kth from end is the head
        if (findNthFromEnd(n, head) != null) {
            ListNode next = head.next;
            head.next = null;
            dummy.next = next;
            return dummy.next;
        }

        return null;
    }

    private ListNode findNthFromEnd(int n, ListNode head) {
        ListNode slow = head, fast = head;

        // Move the fast pointer nth faster
        int count = 0;
        while (fast != null && count < n) {
            count++;
            fast = fast.next;
        }

        // Check if count == n, if not, it means n is larger than linked list length
        if (count < n) return null;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
// @lc code=end

