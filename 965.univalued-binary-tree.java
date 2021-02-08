import java.util.Stack;

/*
 * @lc app=leetcode id=965 lang=java
 *
 * [965] Univalued Binary Tree
 *
 * https://leetcode.com/problems/univalued-binary-tree/description/
 *
 * algorithms
 * Easy (67.60%)
 * Likes:    736
 * Dislikes: 46
 * Total Accepted:    111.8K
 * Total Submissions: 164.8K
 * Testcase Example:  '[1,1,1,1,1,null,1]'
 *
 * A binary tree is univalued if every node in the tree has the same value.
 * 
 * Return true if and only if the given tree is univalued.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [1,1,1,1,1,null,1]
 * Output: true
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [2,2,2,5,2]
 * Output: false
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The number of nodes in the given tree will be in the range [1, 100].
 * Each node's value will be an integer in the range [0, 99].
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
    public boolean isUnivalTree(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return true;
        }
        int value = root.val;
        // return IterativeSolution(root, value);
        return RecursiveSolution(root, value);
    }

    // Time: O(n)
    // Space: O(n)
    // The recursive solution - more interesting than the iterative sol
    public boolean RecursiveSolution(TreeNode root , int value) {
        // Corner Cases
        if (root == null) {
            return true;
        }

        // Pre-order judgement
        if (root.left != null && root.left.val != value) {
            return false;
        }
        if (root.right != null && root.right.val != value) {
            return false;
        }

        return RecursiveSolution(root.left, value) && RecursiveSolution(root.right, value);
    }

    // Time: O(n)
    // Space: O(n)
    // The iterative solution
    public boolean IterativeSolution(TreeNode root, int value) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();

            if (curNode.val != value) {
                return false;
            }

            // Pre-order iterative
            if (curNode.right != null) {
                stack.push(curNode.right);
            }
            if (curNode.left != null) {
                stack.push(curNode.left);
            }
        }
        return true;
    }
}
// @lc code=end

