import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=138 lang=java
 *
 * [138] Copy List with Random Pointer
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 *
 * algorithms
 * Medium (32.33%)
 * Likes:    2543
 * Dislikes: 580
 * Total Accepted:    348.5K
 * Total Submissions: 1.1M
 * Testcase Example:  '[[7,null],[13,0],[11,4],[10,2],[1,0]]\r'
 *
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 * 
 * The Linked List is represented in the input/output as a list of n nodes.
 * Each node is represented as a pair of [val, random_index] where:
 * 
 * 
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) where random
 * pointer points to, or null if it does not point to any node.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: head = []
 * Output: []
 * Explanation: Given linked list is empty (null pointer), so return null.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * -10000 <= Node.val <= 10000
 * Node.random is null or pointing to a node in the linked list.
 * Number of Nodes will not exceed 1000.
 * 
 * 
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    // Time: O(n)
    // Space: O(n)
    // HashMap
    // public Node copyRandomList(Node head) {
    //     if (head == null) return head;

    //     Map<Node, Node> map = new HashMap<>();
    //     Node phead = head;

    //     // put all nodes into HashMap
    //     // store old nodes as key, new nodes as value
    //     while (phead != null) {
    //         map.put(phead, new Node(phead.val));
    //         phead = phead.next;
    //     }

    //     phead = head;
    //     // define next and random
    //     while (phead != null) {
    //         map.get(phead).next = map.get(phead.next);
    //         map.get(phead).random = map.get(phead.random);
    //         phead = phead.next;
    //     }
    //     return map.get(head);
    // }

    // Time: O(n)
    // Space: O(1)
    // Linked List
    public Node copyRandomList(Node head) {
        // Corner Cases
        if (head == null) return head;

        Node phead = head;
        // The first iteration
        // Copy the original list
        while (phead != null) {
            Node next = phead.next;
            Node copy = new Node(phead.val);
            phead.next = copy;
            copy.next = next;
            phead = next;
        }

        phead = head;
        // The second iteration
        // deal with random of copy nodes
        while (phead != null && phead.next != null) {
            // **?**
            if (phead.random != null) phead.next.random = phead.random.next;
            phead = phead.next.next;
        }

        phead = head;
        Node dummy = new Node(0);
        Node dummycopy = dummy;
        // The third iteration
        // seperate original and copy ones
        while (phead != null && phead.next != null) {
            Node next = phead.next.next;
            dummycopy.next = phead.next;
            dummycopy = phead.next;
            phead.next = next;
            phead = next;
        }
        return dummy.next;
    }
}
// @lc code=end

