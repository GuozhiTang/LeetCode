/*
 * @lc app=leetcode id=23 lang=java
 *
 * [23] Merge k Sorted Lists
 *
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 *
 * algorithms
 * Hard (38.07%)
 * Likes:    3757
 * Dislikes: 242
 * Total Accepted:    550.3K
 * Total Submissions: 1.4M
 * Testcase Example:  '[[1,4,5],[1,3,4],[2,6]]'
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * 
 * Example:
 * 
 * 
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
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
    // Binary Merge O(NKlogK)
    public ListNode mergeKLists(ListNode[] lists) {
        // corner case
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        
        int length = lists.length;
        while (length > 1) {
            int mid = (1 + length) / 2;
            for (int i = 0; i < length / 2; i++) {
                lists[i] = mergeTwoLists(lists[i], lists[mid + i]);
            }
            length = mid;
        }
        return lists[0];
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // corner case
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        ListNode head = new ListNode(0);
        ListNode phead = head;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
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
        
        return head.next;
    }
}
// @lc code=end

