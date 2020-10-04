import java.util.Stack;

/*
 * @lc app=leetcode id=234 lang=java
 *
 * [234] Palindrome Linked List
 *
 * https://leetcode.com/problems/palindrome-linked-list/description/
 *
 * algorithms
 * Easy (37.90%)
 * Likes:    2438
 * Dislikes: 317
 * Total Accepted:    354K
 * Total Submissions: 932.1K
 * Testcase Example:  '[1,2]'
 *
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Example 1:
 * 
 * 
 * Input: 1->2
 * Output: false
 * 
 * Example 2:
 * 
 * 
 * Input: 1->2->2->1
 * Output: true
 * 
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
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
    // Space: O(n)
    // Stack
    // public boolean isPalindrome(ListNode head) {
    //     // Corner Cases
    //     if (head == null || head.next == null) return true;

    //     Stack<Integer> stack = new Stack<Integer>();
    //     stack.push(head.val);
    //     // find the middle
    //     ListNode slow = head, fast = head;
    //     while (fast != null && fast.next != null) {
    //         slow = slow.next;
    //         fast = fast.next.next;
    //         stack.push(slow.val);
    //     }

    //     // if length is even, we need to take the top one
    //     if (fast == null && !stack.isEmpty()) stack.pop();

    //     while (slow != null && !stack.isEmpty()) {
    //         if (slow.val != stack.pop()) return false;
    //         slow = slow.next;
    //     }
    //     return true;
    // }

    // Time: O(n)
    // Space: O(1)
    // Reverse
    public boolean isPalindrome(ListNode head) {
        // Corner Cases
        if (head == null || head.next == null) return true;

        // find the middle
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // if length is odd, then move middle to the next
        if (fast != null) slow = slow.next;

        slow = reverseList(slow);

        while (head != null && slow != null) {
            if (head.val != slow.val) return false;
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    // reverse the linked list and make the previous tail as the new head
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
// @lc code=end

