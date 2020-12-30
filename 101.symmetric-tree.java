import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode id=101 lang=java
 *
 * [101] Symmetric Tree
 *
 * https://leetcode.com/problems/symmetric-tree/description/
 *
 * algorithms
 * Easy (47.27%)
 * Likes:    5282
 * Dislikes: 128
 * Total Accepted:    785.1K
 * Total Submissions: 1.6M
 * Testcase Example:  '[1,2,2,3,4,4,3]'
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric
 * around its center).
 * 
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * 
 * 
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   2
 * ⁠/ \ / \
 * 3  4 4  3
 * 
 * 
 * 
 * 
 * But the following [1,2,2,null,3,null,3] is not:
 * 
 * 
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   2
 * ⁠  \   \
 * ⁠  3    3
 * 
 * 
 * 
 * 
 * Follow up: Solve it both recursively and iteratively.
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
    public boolean isSymmetric(TreeNode root) {
        // return recursiveSolution(root);
        return iterativeSolution(root);
    }

    // Time: O(n)
    // Space: O(n)
    // BFS
    private boolean iterativeSolution(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode leftNode = queue.poll();
            TreeNode rightNode = queue.poll();

            if (leftNode == null && rightNode == null) {
                // continue to check whether the queue is empty now
                continue;
            }
            if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                return false;
            }

            queue.add(leftNode.left);
            queue.add(rightNode.right);
            queue.add(leftNode.right);
            queue.add(rightNode.left);
        }
        return true;
    }

    // Time: O(n)
    // Space: O(h)
    private boolean recursiveSolution(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return true;
        }

        return compareTwo(root.left, root.right);
    }

    private boolean compareTwo(TreeNode left, TreeNode right) {
        // Corner Cases
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null || left.val != right.val) {
            return false;
        }

        return compareTwo(left.left, right.right) && compareTwo(left.right, right.left);
    }
}
// @lc code=end

