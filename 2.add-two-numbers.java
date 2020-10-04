/*
 * @lc app=leetcode id=2 lang=java
 *
 * [2] Add Two Numbers
 *
 * https://leetcode.com/problems/add-two-numbers/description/
 *
 * algorithms
 * Medium (32.74%)
 * Likes:    7053
 * Dislikes: 1832
 * Total Accepted:    1.2M
 * Total Submissions: 3.7M
 * Testcase Example:  '[2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Example:
 * 
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
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
    // Time: O(Max(n, m))
    // Space: O(1)
    // 2 -> 4 -> 3
    // 5 -> 6 -> 4
    // =>
    // 7 -> 0 -> 8
    // (342 + 465 = 807)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Corner cases
        if (l1 == null || l2 == null) return (l1 == null) ? l2 : l1;

        ListNode dummy = new ListNode(0);
        ListNode phead = dummy;

        int flag = 0;

        while (l1 != null || l2 != null) {
            if (l1 != null) {
                flag += l1.val;
                l1 = l1.next;
                // System.out.println("flag after l1: " + flag);
            }
            if (l2 != null) {
                // System.out.println("value of l2: " + l2.val);
                flag += l2.val;
                l2 = l2.next;
                // System.out.println("flag after l2: " + flag);
            }
            phead.next = new ListNode(flag%10);
            flag /= 10;
            phead = phead.next;
            // System.out.println("value of phead: " + phead.val);
        }

        // check if the flag still equals to 1
        if (flag == 1) phead.next = new ListNode(1);

        return dummy.next;
    }
}
// @lc code=end

