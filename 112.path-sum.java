import java.util.Stack;

/*
 * @lc app=leetcode id=112 lang=java
 *
 * [112] Path Sum
 *
 * https://leetcode.com/problems/path-sum/description/
 *
 * algorithms
 * Easy (41.54%)
 * Likes:    2799
 * Dislikes: 576
 * Total Accepted:    575K
 * Total Submissions: 1.4M
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,null,1]\n22'
 *
 * Given the root of a binary tree and an integer targetSum, return true if the
 * tree has a root-to-leaf path such that adding up all the values along the
 * path equals targetSum.
 * 
 * A leaf is a node with no children.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [1,2], targetSum = 0
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // return RecursiveSolution(root, targetSum);
        return IterativeSolution(root, targetSum);
    }

    // Time: O(n)
    // Space: O(h)
    // Pre-order iterative solution
    public boolean IterativeSolution(TreeNode root, int targetSum) {
        // Corner Cases
        if (root == null) {
            return false;
        }
        
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();

        nodeStack.push(root);
        sumStack.push(targetSum);

        while (!nodeStack.isEmpty()) {
            TreeNode curNode  = nodeStack.pop();
            int curSum = sumStack.pop();

            if (curNode.left == null && curNode.right == null && curNode.val == curSum) {
                return true;
            }

            if (curNode.right != null) {
                nodeStack.push(curNode.right);
                sumStack.push(curSum - curNode.val);
            }
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
                sumStack.push(curSum - curNode.val);
            }
        }
        return false;
    }

    // Time: O(n)
    // Space: O(h)
    // Recursive Solution
    // References: https://blog.csdn.net/katrina95/article/details/79466714
    public boolean RecursiveSolution(TreeNode root, int targetSum) {
        // Corner Cases
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }

        return RecursiveSolution(root.left, targetSum - root.val) || RecursiveSolution(root.right, targetSum - root.val);
    }
}
// @lc code=end

