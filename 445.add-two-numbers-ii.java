import java.util.Stack;

/*
 * @lc app=leetcode id=445 lang=java
 *
 * [445] Add Two Numbers II
 *
 * https://leetcode.com/problems/add-two-numbers-ii/description/
 *
 * algorithms
 * Medium (52.68%)
 * Likes:    1073
 * Dislikes: 135
 * Total Accepted:    130K
 * Total Submissions: 246.4K
 * Testcase Example:  '[7,2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The most significant digit comes first and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the
 * lists is not allowed.
 * 
 * 
 * 
 * Example:
 * 
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
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
    // Stack - LIFO
    // Time: O(n)
    // Space: O(n)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Corner Cases
        if (l1 == null || l2 == null) return (l1 == null) ? l2 : l1;

        // Use stack to store the linked list in a reverse way
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode phead = dummy;
        Stack<Integer> stackres = new Stack<Integer>();

        int flag = 0;

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) flag += stack1.pop();
            if (!stack2.isEmpty()) flag += stack2.pop();

            stackres.push(flag%10);
            flag /= 10;
        }

        // check if the flag still equals to 1
        if (flag == 1) stackres.push(1);

        // Pop from stackres for the final res
        // Check the empty of stack when before the pop()
        for (int i = 0; i < stackres.capacity(); i++) {
            if (!stackres.isEmpty()) {
                phead.next = new ListNode(stackres.pop());
                phead = phead.next;
            }
        }
        return dummy.next;
    }
}
// @lc code=end

