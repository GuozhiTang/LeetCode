/*
 * @lc app=leetcode id=814 lang=java
 *
 * [814] Binary Tree Pruning
 *
 * https://leetcode.com/problems/binary-tree-pruning/description/
 *
 * algorithms
 * Medium (73.79%)
 * Likes:    1312
 * Dislikes: 50
 * Total Accepted:    79.7K
 * Total Submissions: 109.7K
 * Testcase Example:  '[1,null,0,0,1]'
 *
 * We are given the head node root of a binary tree, where additionally every
 * node's value is either a 0 or a 1.
 * 
 * Return the same tree where every subtree (of the given tree) not containing
 * a 1 has been removed.
 * 
 * (Recall that the subtree of a node X is X, plus every node that is a
 * descendant of X.)
 * 
 * 
 * Example 1:
 * Input: [1,null,0,0,1]
 * Output: [1,null,0,null,1]
 * ⁠
 * Explanation: 
 * Only the red nodes satisfy the property "every subtree not containing a 1".
 * The diagram on the right represents the answer.
 * 
 * 
 * 
 * 
 * 
 * Example 2:
 * Input: [1,0,1,0,0,0,1]
 * Output: [1,null,1,null,1]
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 3:
 * Input: [1,1,0,1,1,0,1,0]
 * Output: [1,1,0,1,1,null,1]
 * 
 * 
 * 
 * 
 * 
 * Note: 
 * 
 * 
 * The binary tree will have at most 200 nodes.
 * The value of each node will only be 0 or 1.
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
    // Time: O(n)
    // Space: O(n)
    // The basic way to prune a tree
    // References: https://blog.csdn.net/a1439775520/article/details/106016904
    public TreeNode pruneTree(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        // Pruning - make that node as null if satisfied the condition
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }

        return root;
    }
}
// @lc code=end

