/*
 * @lc app=leetcode id=99 lang=java
 *
 * [99] Recover Binary Search Tree
 *
 * https://leetcode.com/problems/recover-binary-search-tree/description/
 *
 * algorithms
 * Hard (40.26%)
 * Likes:    2324
 * Dislikes: 89
 * Total Accepted:    204.6K
 * Total Submissions: 480.4K
 * Testcase Example:  '[1,3,null,null,2]'
 *
 * You are given the root of a binary search tree (BST), where exactly two
 * nodes of the tree were swapped by mistake. Recover the tree without changing
 * its structure.
 * 
 * Follow up: A solution using O(n) space is pretty straight forward. Could you
 * devise a constant space solution?
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3
 * makes the BST valid.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2
 * and 3 makes the BST valid.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [2, 1000].
 * -2^31 <= Node.val <= 2^31 - 1
 * 
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    private TreeNode firstNode = null, secondNode = null;
    // private TreeNode preNode = new TreeNode(Integer.MIN_VALUE);
    private TreeNode preNode = null;

    // Time: O(n)
    // Space: O(h)
    // In order traverse
    // Reference: https://leetcode.com/problems/recover-binary-search-tree/discuss/32535/No-Fancy-Algorithm-just-Simple-and-Powerful-In-Order-Traversal
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }

        // In order traverse to find the two elements which need to be swapped.
        inOrderTraverse(root);

        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }

    private void inOrderTraverse(TreeNode curNode) {
        if (curNode == null) {
            return;
        }

        inOrderTraverse(curNode.left);

        // Consider the edge case:
        // [5,3,9,-2147483648,2]
        if (preNode != null && firstNode == null && preNode.val >= curNode.val) {
            firstNode = preNode;
        }
        // Think about the edge use case:
        // 6, 3, 4, 5, 2
        // So when pre >= cur, if first exists, the curNode is the second.
        // if first not exists, the preNode is the first
        if (firstNode != null && preNode.val >= curNode.val) {
            secondNode = curNode;
        }

        preNode = curNode;
        inOrderTraverse(curNode.right);
    }
}
// @lc code=end

