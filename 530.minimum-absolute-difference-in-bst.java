import java.util.*;

/*
 * @lc app=leetcode id=530 lang=java
 *
 * [530] Minimum Absolute Difference in BST
 *
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
 *
 * algorithms
 * Easy (54.05%)
 * Likes:    1152
 * Dislikes: 89
 * Total Accepted:    107.9K
 * Total Submissions: 196.6K
 * Testcase Example:  '[1,null,3,2]'
 *
 * Given a binary search tree with non-negative values, find the minimum
 * absolute difference between values of any two nodes.
 * 
 * Example:
 * 
 * 
 * Input:
 * 
 * ⁠  1
 * ⁠   \
 * ⁠    3
 * ⁠   /
 * ⁠  2
 * 
 * Output:
 * 1
 * 
 * Explanation:
 * The minimum absolute difference is 1, which is the difference between 2 and
 * 1 (or between 2 and 3).
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * There are at least two nodes in this BST.
 * This question is the same as 783:
 * https://leetcode.com/problems/minimum-distance-between-bst-nodes/
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
    public int getMinimumDifference(TreeNode root) {
        // return recursiveSolution(root);
        return iterativeSolution(root);
    }

    // Time: O(n)
    // Space: O(n)
    // Iterative solution - using stack - In-order traverse
    private int iterativeSolution(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode curNode = root; // just make it easier to understand
        int minDiff = Integer.MAX_VALUE;

        while (!stack.isEmpty() || curNode != null) {
            // if there exists curNode, then push nodes to stack till the left end.
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }

            // in-order traverse
            curNode = stack.pop();
            if (pre != null) { // NOTE: must make sure pre is not null before comparasion.
                minDiff = Math.min(minDiff, curNode.val - pre.val); // Since it is ordered, so there is no need to use `Math.abs()`
            }
            pre = curNode;
            curNode = curNode.right;
        }

        return minDiff;
    }

    // Time: O(n)
    // Space: O(h)
    // Recursive Solution - In-order traverse
    // Reference: https://blog.csdn.net/fuxuemingzhu/article/details/69666671
    private TreeNode prev;
    private int minDifference = Integer.MAX_VALUE;
    private int recursiveSolution(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return 0;
        }
        prev = null;
        inOrderRecursion(root);

        return minDifference;
    }

    private void inOrderRecursion(TreeNode root) {
        // Break condition
        if (root == null) {
            return;
        }

        inOrderRecursion(root.left);
        if (prev != null) {
            minDifference = Math.min(minDifference, root.val - prev.val);
        }
        prev = root;
        inOrderRecursion(root.right);
    }
}
// @lc code=end

