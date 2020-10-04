import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode id=144 lang=java
 *
 * [144] Binary Tree Preorder Traversal
 *
 * https://leetcode.com/problems/binary-tree-preorder-traversal/description/
 *
 * algorithms
 * Medium (53.87%)
 * Likes:    1269
 * Dislikes: 54
 * Total Accepted:    455K
 * Total Submissions: 836.2K
 * Testcase Example:  '[1,null,2,3]'
 *
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * 
 * Example:
 * 
 * 
 * Input: [1,null,2,3]
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3
 * 
 * Output: [1,2,3]
 * 
 * 
 * Follow up: Recursive solution is trivial, could you do it iteratively?
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
    private List<Integer> result = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        recursiveSol(root);
        return iterativeSol(root);
    }

    // Time: O(n)
    // Space: O(n)
    // Iteration
    private List<Integer> iterativeSol(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // Corner Cases
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();
            if (curNode != null) {
                res.add(curNode.val);
                stack.push(curNode.right);
                stack.push(curNode.left);
            }
        }
        return res;
    }

    // Time: O(n)
    // Space: O(n)
    // Recursion
    private List<Integer> recursiveSol(TreeNode root) {
        // Corner Cases
        if (root == null) {
            return result;
        }

        result.add(root.val);
        recursiveSol(root.left);
        recursiveSol(root.right);

        return result;
    }
}
// @lc code=end

