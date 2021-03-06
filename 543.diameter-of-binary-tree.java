/*
 * @lc app=leetcode id=543 lang=java
 *
 * [543] Diameter of Binary Tree
 *
 * https://leetcode.com/problems/diameter-of-binary-tree/description/
 *
 * algorithms
 * Easy (48.64%)
 * Likes:    4343
 * Dislikes: 272
 * Total Accepted:    444.1K
 * Total Submissions: 903.4K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 
 * Given a binary tree, you need to compute the length of the diameter of the
 * tree. The diameter of a binary tree is the length of the longest path
 * between any two nodes in a tree. This path may or may not pass through the
 * root.
 * 
 * 
 * 
 * Example:
 * Given a binary tree 
 * 
 * ⁠         1
 * ⁠        / \
 * ⁠       2   3
 * ⁠      / \     
 * ⁠     4   5    
 * 
 * 
 * 
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * 
 * 
 * Note:
 * The length of path between two nodes is represented by the number of edges
 * between them.
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

    private int diameter = 0;

    // Time: O(n)
    // Space: O(h)
    // Divide and Conquer
    // Reference: https://blog.csdn.net/mine_song/article/details/65929392
    // Can compared with No.124 (same idea in that Hard question)
    public int diameterOfBinaryTree(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return 0;
        }

        getMaxDiameter(root);

        return diameter;
    }

    private int getMaxDiameter(TreeNode root) {
        // Break condition
        if (root == null) {
            return 0;
        }

        int left = getMaxDiameter(root.left);
        int right = getMaxDiameter(root.right);

        diameter = Math.max(diameter, left + right);

        return Math.max(left, right) + 1;
    }
}
// @lc code=end

