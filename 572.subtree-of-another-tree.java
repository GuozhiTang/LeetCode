import java.util.Stack;

/*
 * @lc app=leetcode id=572 lang=java
 *
 * [572] Subtree of Another Tree
 *
 * https://leetcode.com/problems/subtree-of-another-tree/description/
 *
 * algorithms
 * Easy (43.69%)
 * Likes:    1881
 * Dislikes: 89
 * Total Accepted:    182.6K
 * Total Submissions: 417.3K
 * Testcase Example:  '[3,4,5,1,2]\n[4,1,2]'
 *
 * 
 * Given two non-empty binary trees s and t, check whether tree t has exactly
 * the same structure and node values with a subtree of s. A subtree of s is a
 * tree consists of a node in s and all of this node's descendants. The tree s
 * could also be considered as a subtree of itself.
 * 
 * 
 * Example 1:
 * 
 * Given tree s:
 * 
 * ⁠    3
 * ⁠   / \
 * ⁠  4   5
 * ⁠ / \
 * ⁠1   2
 * 
 * Given tree t:
 * 
 * ⁠  4 
 * ⁠ / \
 * ⁠1   2
 * 
 * Return true, because t has the same structure and node values with a subtree
 * of s.
 * 
 * 
 * Example 2:
 * 
 * Given tree s:
 * 
 * ⁠    3
 * ⁠   / \
 * ⁠  4   5
 * ⁠ / \
 * ⁠1   2
 * ⁠   /
 * ⁠  0
 * 
 * Given tree t:
 * 
 * ⁠  4
 * ⁠ / \
 * ⁠1   2
 * 
 * Return false.
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        // return isSubtreeIterative(s, t);
        return isSubtreeRecursive(s, t);
    }

    public boolean isSubtreeRecursive(TreeNode s, TreeNode t) {
        // Corner Cases
        // we are iterate through `s`
        // so when `s` is null, it means that there is no node's subtree is the same as t
        // It is also a break condition of the recursion.
        if (s == null) {
            return false;
        }

        if (isSameTree(s, t)) {
            return true;
        }

        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    // Time: Worst - O(s*t)
    // Space: O(n)
    // The iterative way
    public boolean isSubtreeIterative(TreeNode s, TreeNode t) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(s);

        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();
            if (isSameTree(curNode, t)) {
                return true;
            }

            // Pre-order iteration
            if (curNode.right != null) {
                stack.push(curNode.right);
            }
            if (curNode.left != null) {
                stack.push(curNode.left);
            }
        }

        return false;
    }

    // The helper function to jude whether two nodes' tree archietures are the same
    public boolean isSameTree(TreeNode node1, TreeNode node2) {
        // Corner Cases
        // Also the break condition of the recursion
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        if (node1.val != node2.val) {
            return false;
        }

        // If not, then continue the recursion
        return isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
    }
}
// @lc code=end

