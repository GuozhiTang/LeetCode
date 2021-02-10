/*
 * @lc app=leetcode id=669 lang=java
 *
 * [669] Trim a Binary Search Tree
 *
 * https://leetcode.com/problems/trim-a-binary-search-tree/description/
 *
 * algorithms
 * Easy (63.14%)
 * Likes:    2643
 * Dislikes: 208
 * Total Accepted:    149.8K
 * Total Submissions: 232.1K
 * Testcase Example:  '[1,0,2]\n1\n2'
 *
 * Given the root of a binary search tree and the lowest and highest boundaries
 * as low and high, trim the tree so that all its elements lies in [low, high].
 * Trimming the tree should not change the relative structure of the elements
 * that will remain in the tree (i.e., any node's descendant should remain a
 * descendant). It can be proven that there is a unique answer.
 * 
 * Return the root of the trimmed binary search tree. Note that the root may
 * change depending on the given bounds.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,0,2], low = 1, high = 2
 * Output: [1,null,2]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
 * Output: [3,2,null,1]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [1], low = 1, high = 2
 * Output: [1]
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: root = [1,null,2], low = 1, high = 3
 * Output: [1,null,2]
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: root = [1,null,2], low = 2, high = 4
 * Output: [2]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree in the range [1, 10^4].
 * 0 <= Node.val <= 10^4
 * The value of each node in the tree is unique.
 * root is guaranteed to be a valid binary search tree.
 * 0 <= low <= high <= 10^4
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
    // Space: O(h)
    // Triming/Purning
    public TreeNode trimBST(TreeNode root, int low, int high) {
        // Corner Cases
        if (root == null) {
            return null;
        }

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        if (root.val <= high && root.val >= low) {
            return root;
        } else if (root.val < low) { // Definition of BST, left < root < right
            return root.right;
        } else {
            return root.left;
        }
    }
}
// @lc code=end

