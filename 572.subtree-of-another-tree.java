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
        // return isSubtreeRecursive(s, t);
        return isSubtreeIterative(s, t);
    }

    // Time: O(m*n)
    // Space: O(n)
    // iteratively
    // check whether there is a node in s whose subtree is the same as t
    public boolean isSubtreeIterative(TreeNode s, TreeNode t) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(s);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isSameTree(node, t)) {
                return true;
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return false;
    }

    // Time: O(m*n)
    // Space: O(n)
    // recursively
    // check whether there is a node in s whose subtree is the same as t
    public boolean isSubtreeRecursive(TreeNode s, TreeNode t) {
        if (s == null){
            return false;
        }
        if (isSameTree(s, t)) {
            return true;
        }
        return isSubtreeRecursive(s.left, t) || isSubtreeRecursive(s.right, t);
    }

    // for each node during pre-order traversal of s,
    // validate if subtree starts with this node is the same with t
    public boolean isSameTree(TreeNode s, TreeNode t) {
        // Corner Cases
        if (s == null && t == null){
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}
// @lc code=end

